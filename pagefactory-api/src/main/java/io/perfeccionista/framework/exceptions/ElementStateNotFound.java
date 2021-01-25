package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementStateNotFound extends Reason {

    static ElementStateNotFoundException exception(@NotNull String message) {
        return new ElementStateNotFoundException(message);
    }

    static ElementStateNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementStateNotFoundException(message, cause);
    }

    class ElementStateNotFoundException extends PerfeccionistaRuntimeException implements ElementStateNotFound {

        ElementStateNotFoundException(String message) {
            super(message);
        }

        ElementStateNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
