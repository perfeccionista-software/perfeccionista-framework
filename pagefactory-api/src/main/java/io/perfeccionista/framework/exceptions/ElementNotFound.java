package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotFound extends Reason {

    static ElementNotFoundException exception(@NotNull String message) {
        return new ElementNotFoundException(message);
    }

    static ElementNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotFoundException(message, cause);
    }

    class ElementNotFoundException extends PerfeccionistaRuntimeException implements ElementNotFound {

        ElementNotFoundException(String message) {
            super(message);
        }

        ElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
