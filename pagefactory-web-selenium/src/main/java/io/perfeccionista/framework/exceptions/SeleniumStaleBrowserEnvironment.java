package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumStaleBrowserEnvironment extends Reason {

    static SeleniumStaleBrowserEnvironmentException exception(@NotNull String message) {
        return new SeleniumStaleBrowserEnvironmentException(message);
    }

    static SeleniumStaleBrowserEnvironmentException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumStaleBrowserEnvironmentException(message, cause);
    }

    class SeleniumStaleBrowserEnvironmentException extends PerfeccionistaRuntimeException implements SeleniumStaleBrowserEnvironment {

        SeleniumStaleBrowserEnvironmentException(String message) {
            super(message);
        }

        SeleniumStaleBrowserEnvironmentException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
