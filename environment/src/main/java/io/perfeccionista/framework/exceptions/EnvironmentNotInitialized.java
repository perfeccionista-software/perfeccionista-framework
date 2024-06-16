package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.EnvironmentNotInitializedException;
import org.jetbrains.annotations.NotNull;

public interface EnvironmentNotInitialized extends Reason {

    static EnvironmentNotInitializedException exception(@NotNull String message) {
        return new EnvironmentNotInitializedException(message);
    }

    static EnvironmentNotInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EnvironmentNotInitializedException(message, cause);
    }

}
