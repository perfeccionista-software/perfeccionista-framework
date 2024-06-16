package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.EnvironmentNotConfiguredException;
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

}
