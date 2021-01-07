package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.timeouts.type.CheckDelayTimeout;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.invocation.runner.InvocationName.InvocationNameType.GETTER;
import static java.lang.String.format;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * TODO: JavaDoc
 */
public class CheckInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(CheckInvocationRunner.class);

    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @NotNull InvocationName name, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {

        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();
        logger.debug(() -> format("Check action started. Timeout = %s. Delay = %s.", getFormattedDuration(timeout), getFormattedDuration(delay)));

        long invocationStartTime = 0;

        while (deadline >= currentTime) {
            try {
                invocationStartTime = System.nanoTime();
                T result = supplier.get();
                logInvocationExecution(name, invocationStartTime, "SUCCESS");
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                logInvocationExecution(name, invocationStartTime, "EXCEPTION");
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Exception e) {
                logInvocationExecution(name, invocationStartTime, "UNEXPECTED EXCEPTION");
                logger.error(() -> format("Check action cycle finished with unexpected exception: %s", e));
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }

        logger.error(() -> "Check action finished with exception");
        exceptionCollector.throwIfSingleException();
        throw exceptionCollector.getExceptionSequence();
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
                .getTimeout(CheckDelayTimeout.class);
    }

    protected String getFormattedDuration(Duration duration) {
        return format("%02d:%02d.%03d", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

}
