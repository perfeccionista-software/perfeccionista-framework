package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.timeouts.CheckDelayTimeout;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.utils.ThreadUtils.sleep;
import static java.lang.String.format;

public class SeleniumCheckInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(SeleniumCheckInvocationRunner.class);

    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @NotNull InvocationName name, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {

        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();
        if (name.isNotEmpty()) {
            logger.debug(name::toString);
        }
        logger.debug(() -> format("Check action started. Timeout = %s. Delay = %s.", getFormattedDuration(timeout), getFormattedDuration(delay)));

        while (deadline >= currentTime) {
            try {
                T result = supplier.get();
                logger.debug(() -> "Check action finished");
                return result;
            } catch (final PerfeccionistaException e) {
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Exception e) {
                logger.error(() -> format("Check action cycle finished with unexpected exception: %s", e));
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }

        logger.debug(() -> "Check action finished with exception");
        throw exceptionCollector.getException();
    }

    protected void processException(PerfeccionistaException exception) {
        if (null == exceptionCollector) {
            exceptionCollector = new ExceptionCollector(exception);
        }
        exceptionCollector.processException(exception);
    }

    protected Duration getDelayTimeout(Environment environment) {
        return environment.getEnvironmentConfiguration()
                .getTimeouts()
                .getTimeout(CheckDelayTimeout.class);
    }

    protected String getFormattedDuration(Duration duration) {
        return format("%02d:%02d.%03d", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

}
