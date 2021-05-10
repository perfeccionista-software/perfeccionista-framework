package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNameNotFound extends Reason {

    static ElementNameNotFoundException exception(@NotNull String message) {
        return new ElementNameNotFoundException(message);
    }

    static ElementNameNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNameNotFoundException(message, cause);
    }

    class ElementNameNotFoundException extends PerfeccionistaRuntimeException implements ElementNameNotFound {

        ElementNameNotFoundException(String message) {
            super(message);
        }

        ElementNameNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}


