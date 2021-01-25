package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementPropertyNotFound extends Reason {

    static ElementPropertyNotFoundException exception(@NotNull String message) {
        return new ElementPropertyNotFoundException(message);
    }

    static ElementPropertyNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementPropertyNotFoundException(message, cause);
    }

    class ElementPropertyNotFoundException extends PerfeccionistaRuntimeException implements ElementPropertyNotFound {

        ElementPropertyNotFoundException(String message) {
            super(message);
        }

        ElementPropertyNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
