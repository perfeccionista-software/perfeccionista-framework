package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectInvocationRunnerLogic;
import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.exceptions.attachments.BigTextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebAllureAttachmentProcessor;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.CheckDelayTimeout;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Supplier;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.INCORRECT_INVOCATION_RUNNER_LOGIC;
import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.*;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;
import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class WebAllureCheckInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(WebAllureCheckInvocationRunner.class);
    private static final ThreadLocal<Deque<InvocationInfo>> runCheckInvocationStack = new ThreadLocal<>();

    private static final StartInvocationInfoVisitor checkInvocationVisitor = new StartInvocationInfoVisitor("Check action");
    private static final AllureInvocationStatisticsFormatter allureStatisticsFormatter = new AllureInvocationStatisticsFormatter();
    private static final AllureInvocationNameFormatter allureNameFormatter = new AllureInvocationNameFormatter();


    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @NotNull InvocationInfo invocation, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {

        Deque<InvocationInfo> invocationDeque = getThreadLocalInvocationStack();

        // вложенный вызов
        if (!invocationDeque.isEmpty()) {
            var lastInvocation = invocationDeque.getLast();
            if (!lastInvocation.equals(invocation)) {
                invocationDeque.addLast(invocation);
                lastInvocation = invocation;
            }
            lastInvocation.start(checkInvocationVisitor);
            try {
                T result = supplier.get();
                lastInvocation.success();
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                lastInvocation.exception(e);
                processException(e);
            } catch (final Throwable e) {
                lastInvocation.exception(e);
                throw e;
            }
            exceptionCollector.throwLastException();
            throw IncorrectInvocationRunnerLogic.exception(INCORRECT_INVOCATION_RUNNER_LOGIC.getMessage()).addLastAttachmentEntry(BigTextAttachmentEntry
                    .of("All Exception Messages", exceptionCollector.generateExceptionSequenceMessage()));
        }

        // первый вызов
        invocationDeque.addLast(invocation);

        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();

        while (deadline >= currentTime) {
            invocation.start(checkInvocationVisitor);
            try {
                T result = supplier.get();
                invocation.success();
                processInvocationExecution(invocationDeque);
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                invocation.exception(e);
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Throwable e) {
                invocation.exception(e);
                processInvocationExecution(invocationDeque);
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }
        processInvocationExecution(invocationDeque);
        exceptionCollector.throwLastException(new WebAllureAttachmentProcessor(environment, invocation));
        throw IncorrectInvocationRunnerLogic.exception(INCORRECT_INVOCATION_RUNNER_LOGIC.getMessage()).addLastAttachmentEntry(BigTextAttachmentEntry
                .of("All Exception Messages", exceptionCollector.generateExceptionSequenceMessage()));
    }

    protected @NotNull Deque<InvocationInfo> getThreadLocalInvocationStack() {
        Deque<InvocationInfo> invocationDeque = runCheckInvocationStack.get();
        if (Objects.isNull(invocationDeque)) {
            invocationDeque = new ArrayDeque<>();
            runCheckInvocationStack.set(invocationDeque);
        }
        return invocationDeque;
    }

    protected void processInvocationExecution(Deque<InvocationInfo> invocations) {
        while (!invocations.isEmpty()) {
            String indent = getIndent(invocations.size());
            var processedInvocation = invocations.removeLast();
            getLifecycle().updateStep(processedInvocation.getUuid(),
                    stepResult -> stepResult.setName(processedInvocation.getFormattedName(allureNameFormatter)));
            var invocationResult = processedInvocation.getResults().getLast();
            if (SUCCESS == invocationResult.getStatus()) {
                getLifecycle().updateStep(processedInvocation.getUuid(), step -> step.setStatus(Status.PASSED));
            } else if (EXCEPTION == invocationResult.getStatus()) {
                var throwable = invocationResult.getThrowable()
                        .orElseThrow(() -> PreconditionViolation.exception("Exception status is set together with the exception"));
                getLifecycle().updateStep(s -> s
                        .setStatus(getStatus(throwable).orElse(Status.BROKEN))
                        .setStatusDetails(getStatusDetails(throwable).orElse(null)));
                Allure.addAttachment("Execution statistics", processedInvocation.getFormattedStatistics(allureStatisticsFormatter));
            } else {
                throw PreconditionViolation.exception("Processed invocation can't have status 'NEW'");
            }
            processedInvocation.close(invocationInfo -> getLifecycle().stopStep(invocationInfo.getUuid()));
            logger.info(() -> indent + processedInvocation);
        }
        runCheckInvocationStack.remove();
    }

    protected String getIndent(int length) {
        return "    ".repeat(Math.max(0, length-1));
    }

    protected void processException(PerfeccionistaException exception) {
        if (null == exceptionCollector) {
            exceptionCollector = new ExceptionCollector(exception);
        }
        exceptionCollector.processException(exception);
    }

    protected Duration getDelayTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class)
                .getTimeout(CheckDelayTimeout.class);
    }

}
