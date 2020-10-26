package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.timeouts.LogicDelayTimeout;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

import java.time.Duration;
import java.util.function.Supplier;

import static java.lang.String.format;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * TODO: JavaDoc
 */
public class LogicInvocationRunner implements InvocationRunner {
    private static final Logger logger = LoggerFactory.getLogger(LogicInvocationRunner.class);

    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @NotNull InvocationName name, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {

        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();
        if (name.isNotEmpty()) {
            logger.info(name::toString);
        }
        logger.debug(() -> format("Logic action started. Timeout = %s. Delay = %s.", getFormattedDuration(timeout), getFormattedDuration(delay)));

        while (deadline >= currentTime) {
            try {
                T result = supplier.get();
                logger.debug(() -> "Logic action finished");
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
                processException(e);
                if (!e.isProcessed()) {
                    break;
                }
            } catch (final Exception e) {
                logger.error(() -> format("Logic action cycle finished with unexpected exception: %s", e));
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }

        logger.error(() -> "Logic action finished with exception");
        exceptionCollector.throwIfSingleException();
        throw exceptionCollector.getExceptionSequence();
    }

    private void processException(PerfeccionistaException exception) {
        if (null == exceptionCollector) {
            exceptionCollector = new ExceptionCollector(exception);
        }
        exceptionCollector.processException(exception);
    }

    private static Duration getDelayTimeout(Environment environment) {
        return environment.getEnvironmentConfiguration()
                .getTimeouts()
                .getTimeout(LogicDelayTimeout.class);
    }

    protected String getFormattedDuration(Duration duration) {
        return format("%02d:%02d.%03d", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

}
