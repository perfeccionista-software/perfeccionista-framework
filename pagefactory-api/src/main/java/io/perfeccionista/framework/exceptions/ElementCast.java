package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementCast extends Reason {

    static ElementCastException exception(@NotNull String message) {
        return new ElementCastException(message);
    }

    static ElementCastException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementCastException(message, cause);
    }

    class ElementCastException extends PerfeccionistaRuntimeException implements ElementCast {

        ElementCastException(String message) {
            super(message);
        }

        ElementCastException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
