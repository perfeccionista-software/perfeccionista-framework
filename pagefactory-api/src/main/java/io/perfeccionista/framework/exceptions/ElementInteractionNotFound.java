package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementInteractionNotFound extends Reason {

    static ElementInteractionNotFoundException exception(@NotNull String message) {
        return new ElementInteractionNotFoundException(message);
    }

    static ElementInteractionNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementInteractionNotFoundException(message, cause);
    }

    class ElementInteractionNotFoundException extends PerfeccionistaRuntimeException implements ElementInteractionNotFound {

        ElementInteractionNotFoundException(String message) {
            super(message);
        }

        ElementInteractionNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
