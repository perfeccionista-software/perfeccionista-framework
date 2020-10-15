package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface IncorrectServiceConfiguration extends Reason {

    static IncorrectServiceConfigurationException exception(@NotNull String message) {
        return new IncorrectServiceConfigurationException(message);
    }

    static IncorrectServiceConfigurationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectServiceConfigurationException(message, cause);
    }

    class IncorrectServiceConfigurationException extends PerfeccionistaRuntimeException implements IncorrectServiceConfiguration {

        IncorrectServiceConfigurationException(String message) {
            super(message);
        }

        IncorrectServiceConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}