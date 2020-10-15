package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface EnvironmentNotConfigured extends Reason {

    static EnvironmentNotConfiguredException exception(@NotNull String message) {
        return new EnvironmentNotConfiguredException(message);
    }

    static EnvironmentNotConfiguredException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EnvironmentNotConfiguredException(message, cause);
    }

    class EnvironmentNotConfiguredException extends PerfeccionistaRuntimeException implements EnvironmentNotConfigured {

        EnvironmentNotConfiguredException(String message) {
            super(message);
        }

        EnvironmentNotConfiguredException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}