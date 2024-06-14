package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.EnvironmentAlreadyInitializedException;
import org.jetbrains.annotations.NotNull;

public interface EnvironmentAlreadyInitialized extends Reason {

    static EnvironmentAlreadyInitializedException exception(@NotNull String message) {
        return new EnvironmentAlreadyInitializedException(message);
    }

    static EnvironmentAlreadyInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EnvironmentAlreadyInitializedException(message, cause);
    }

}
