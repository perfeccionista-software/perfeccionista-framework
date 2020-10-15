package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface EnvironmentNotInitialized extends Reason {

    static EnvironmentNotInitializedException exception(@NotNull String message) {
        return new EnvironmentNotInitializedException(message);
    }

    static EnvironmentNotInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EnvironmentNotInitializedException(message, cause);
    }

    class EnvironmentNotInitializedException extends PerfeccionistaRuntimeException implements EnvironmentNotInitialized {

        EnvironmentNotInitializedException(String message) {
            super(message);
        }

        EnvironmentNotInitializedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
