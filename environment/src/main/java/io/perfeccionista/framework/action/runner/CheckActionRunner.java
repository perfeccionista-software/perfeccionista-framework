package io.perfeccionista.framework.action.runner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.action.timeouts.CheckDelayTimeout;
import io.perfeccionista.framework.exceptions.base.ExceptionCollector;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

import java.time.Duration;
import java.util.function.Supplier;

import static java.lang.String.format;
import static org.junit.platform.commons.util.StringUtils.isBlank;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * TODO: JavaDoc
 */
public class CheckActionRunner implements ActionRunner {
    private static final Logger logger = LoggerFactory.getLogger(CheckActionRunner.class);

    private ExceptionCollector exceptionCollector = null;

    @Override
    public <T> T run(@NotNull Environment environment, @Nullable String name, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {

        Duration delay = getDelayTimeout(environment);

        // We need this for one attempt if timeout = 0
        long currentTime = System.nanoTime();
        long deadline = currentTime + timeout.toNanos();
        logger.debug(() -> isBlank(name)
                ? format("Check action started. Timeout = %s. Delay = %s", timeout, delay)
                : format("Check action '%s' started. Timeout = %s. Delay = %s", name, timeout, delay));

        while (deadline >= currentTime) {
            try {
                T result = supplier.get();
                logger.debug(() -> isBlank(name)
                        ? "Check action finished"
                        : format("Check action '%s' finished", name));
                return result;
            } catch (final PerfeccionistaException e) {
                processException(e);
                if (e.isProcessed()) {
                    logger.trace(() -> isBlank(name)
                            ? format("Check action cycle failed with processed exception: %s", e)
                            : format("Check action cycle '%s' failed with processed exception: %s", name, e));
                } else {
                    logger.trace(() -> isBlank(name)
                            ? format("Check action cycle failed with non-processed exception: %s", e)
                            : format("Check action cycle '%s' failed with non-processed exception: %s", name, e));
                    break;
                }
            } catch (final Exception e) {
                logger.error(() -> isBlank(name)
                        ? format("Check action cycle finished with unexpected exception: %s", e)
                        : format("Check action cycle '%s' finished with unexpected exception: %s", name, e));
                throw e;
            }
            sleep(delay);
            currentTime = System.nanoTime();
        }

        logger.debug(() -> isBlank(name)
                ? "Check action finished with exception"
                : format("Check action '%s' finished with exception", name));
        throw exceptionCollector.getExceptionSequence();
    }

    private void processException(PerfeccionistaException exception) {
        if (null == exceptionCollector) {
            exceptionCollector = new ExceptionCollector(exception);
        }
        exceptionCollector.processException(exception);
    }

    private Duration getDelayTimeout(Environment environment) {
        return environment.getEnvironmentConfiguration()
                .getTimeouts()
                .getTimeout(CheckDelayTimeout.class);
    }

}
