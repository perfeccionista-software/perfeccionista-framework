package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectInvocationRunnerLogic;
import io.perfeccionista.framework.exceptions.attachments.BigTextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.LogicDelayTimeout;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Supplier;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.INCORRECT_INVOCATION_RUNNER_LOGIC;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

public class WebAllureLogicInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(WebAllureLogicInvocationRunner.class);
    private static final ThreadLocal<Deque<InvocationInfo>> runLogicInvocationStack = new ThreadLocal<>();

    private static final StartInnerInvocationInfoVisitor startInnerInvocationVisitor = new StartInnerInvocationInfoVisitor();
    private static final SuccessInnerInvocationInfoVisitor successInnerInvocationVisitor = new SuccessInnerInvocationInfoVisitor();
    private static final ExceptionInnerInvocationInfoLogicVisitor exceptionInnerInvocationVisitor = new ExceptionInnerInvocationInfoLogicVisitor();
    private static final CloseInnerInvocationInfoVisitor closeInnerInvocationVisitor = new CloseInnerInvocationInfoVisitor();

    private static final StartInvocationInfoVisitor startInvocationVisitor = new StartInvocationInfoVisitor();
    private static final SuccessInvocationInfoVisitor successInvocationVisitor = new SuccessInvocationInfoVisitor();
    private static final ExceptionInvocationInfoVisitor exceptionInvocationVisitor = new ExceptionInvocationInfoVisitor();
    private static final CloseInvocationInfoLogicVisitor closeInvocationVisitor = new CloseInvocationInfoLogicVisitor();

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
            try {
                lastInvocation.start(startInnerInvocationVisitor);
                T result = supplier.get();
                lastInvocation.success(successInnerInvocationVisitor);
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                lastInvocation.exception(e, exceptionInnerInvocationVisitor);
                processException(e);
            } catch (final Throwable e) {
                lastInvocation.exception(e, exceptionInnerInvocationVisitor);
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
            try {
                invocation.start(startInvocationVisitor);
                T result = supplier.get();
                invocation.success(successInvocationVisitor);
                processInvocationExecution(invocationDeque);
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                invocation.exception(e, exceptionInvocationVisitor);
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Throwable e) {
                invocation.exception(e, exceptionInvocationVisitor);
                processInvocationExecution(invocationDeque);
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }
        processInvocationExecution(invocationDeque);
        exceptionCollector.throwLastException();
        throw IncorrectInvocationRunnerLogic.exception(INCORRECT_INVOCATION_RUNNER_LOGIC.getMessage()).addLastAttachmentEntry(BigTextAttachmentEntry
                .of("All Exception Messages", exceptionCollector.generateExceptionSequenceMessage()));
    }

    protected @NotNull Deque<InvocationInfo> getThreadLocalInvocationStack() {
        Deque<InvocationInfo> invocationDeque = runLogicInvocationStack.get();
        if (Objects.isNull(invocationDeque)) {
            invocationDeque = new ArrayDeque<>();
            runLogicInvocationStack.set(invocationDeque);
        }
        return invocationDeque;
    }

    protected void processInvocationExecution(Deque<InvocationInfo> invocations) {
        while (!invocations.isEmpty()) {
            String indent = getIndent(invocations.size());
            var processedInvocation = invocations.removeLast();
            if (invocations.isEmpty()) {
                processedInvocation.close(closeInvocationVisitor);
            } else {
                processedInvocation.close(closeInnerInvocationVisitor);
            }
            logger.info(() -> indent + processedInvocation);
        }
        runLogicInvocationStack.remove();
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
                .getTimeout(LogicDelayTimeout.class);
    }

}
