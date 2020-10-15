package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MethodNotFound extends Reason {

    static MethodNotFoundException exception(@NotNull String message) {
        return new MethodNotFoundException(message);
    }

    static MethodNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MethodNotFoundException(message, cause);
    }

    class MethodNotFoundException extends PerfeccionistaRuntimeException implements MethodNotFound {

        MethodNotFoundException(String message) {
            super(message);
        }

        MethodNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
