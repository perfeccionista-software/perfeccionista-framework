package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementBoundsParse extends Reason {

    static ElementBoundsParseException exception(@NotNull String message) {
        return new ElementBoundsParseException(message);
    }

    static ElementBoundsParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementBoundsParseException(message, cause);
    }

    class ElementBoundsParseException extends PerfeccionistaRuntimeException implements ElementBoundsParse {

        ElementBoundsParseException(String message) {
            super(message);
        }

        ElementBoundsParseException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
