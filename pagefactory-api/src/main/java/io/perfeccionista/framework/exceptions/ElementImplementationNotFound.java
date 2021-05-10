package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementImplementationNotFound extends Reason {

    static ElementImplementationNotFoundException exception(@NotNull String message) {
        return new ElementImplementationNotFoundException(message);
    }

    static ElementImplementationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementImplementationNotFoundException(message, cause);
    }

    class ElementImplementationNotFoundException extends PerfeccionistaRuntimeException implements ElementImplementationNotFound {

        ElementImplementationNotFoundException(String message) {
            super(message);
        }

        ElementImplementationNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
