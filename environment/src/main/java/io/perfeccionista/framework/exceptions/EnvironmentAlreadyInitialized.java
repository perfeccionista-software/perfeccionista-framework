package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface EnvironmentAlreadyInitialized extends Reason {

    static EnvironmentAlreadyInitialized.EnvironmentAlreadyInitializedException exception(@NotNull String message) {
        return new EnvironmentAlreadyInitialized.EnvironmentAlreadyInitializedException(message);
    }

    static EnvironmentAlreadyInitialized.EnvironmentAlreadyInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EnvironmentAlreadyInitialized.EnvironmentAlreadyInitializedException(message, cause);
    }

    class EnvironmentAlreadyInitializedException extends PerfeccionistaRuntimeException implements EnvironmentAlreadyInitialized {

        EnvironmentAlreadyInitializedException(String message) {
            super(message);
        }

        EnvironmentAlreadyInitializedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
