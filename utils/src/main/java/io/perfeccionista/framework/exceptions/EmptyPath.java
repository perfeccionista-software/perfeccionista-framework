package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface EmptyPath extends Reason {

    static EmptyPathException exception(@NotNull String message) {
        return new EmptyPathException(message);
    }

    static EmptyPathException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EmptyPathException(message, cause);
    }

    class EmptyPathException extends PerfeccionistaRuntimeException implements EmptyPath {

        EmptyPathException(String message) {
            super(message);
        }

        EmptyPathException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
