package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.IncorrectServiceConfigurationException;
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

}
