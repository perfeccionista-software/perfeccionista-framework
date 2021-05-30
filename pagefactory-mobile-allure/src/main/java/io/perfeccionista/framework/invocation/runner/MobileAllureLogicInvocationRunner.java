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
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.INCORRECT_INVOCATION_RUNNER_LOGIC;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;
import static java.lang.String.format;

public class MobileAllureLogicInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MobileAllureLogicInvocationRunner.class);

    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @NotNull InvocationName name, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {
        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();
        long invocationStartTime = 0;

        while (deadline >= currentTime) {
            try {
                invocationStartTime = System.nanoTime();
                T result = supplier.get();
//                logInvocationExecution(name, invocationStartTime, "SUCCESS");
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Throwable e) {
                logInvocationExecution(name, invocationStartTime, "UNEXPECTED EXCEPTION");
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }

        logInvocationExecution(name, invocationStartTime, "EXCEPTION");
        exceptionCollector.throwLastException(new MobileAllureAttachmentProcessor(environment, name));
        throw IncorrectInvocationRunnerLogic.exception(INCORRECT_INVOCATION_RUNNER_LOGIC.getMessage()).addLastAttachmentEntry(BigTextAttachmentEntry
                .of("All Exception Messages", exceptionCollector.generateExceptionSequenceMessage()));
    }

    protected void logInvocationExecution(InvocationName name, long invocationStartTime, String status) {
        logger.info(() -> name.toString() + ": " + ((System.nanoTime() - invocationStartTime)/1_000_000) + " ms -> [" + status + "]");
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

    protected String getFormattedDuration(Duration duration) {
        return format("%02d:%02d.%03d", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

}
