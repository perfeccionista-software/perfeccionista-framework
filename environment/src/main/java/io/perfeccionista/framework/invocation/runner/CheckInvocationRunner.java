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
        if (name.isNotEmpty() && GETTER != name.getType()) {
            logger.info(name::toString);
        }
        logger.debug(() -> format("Check action started. Timeout = %s. Delay = %s.", getFormattedDuration(timeout), getFormattedDuration(delay)));

        while (deadline >= currentTime) {
            try {
                T result = supplier.get();
                logger.debug(() -> "Check action finished");
                return result;
            } catch (final PerfeccionistaRuntimeException | PerfeccionistaAssertionError e) {
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

        logger.error(() -> "Check action finished with exception");
        exceptionCollector.throwIfSingleException();
        throw exceptionCollector.getExceptionSequence();
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