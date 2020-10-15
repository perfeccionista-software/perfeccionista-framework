package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface StringValueParse extends Reason {

    static StringValueParseException exception(@NotNull String message) {
        return new StringValueParseException(message);
    }

    static StringValueParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new StringValueParseException(message, cause);
    }

    class StringValueParseException extends PerfeccionistaRuntimeException implements StringValueParse {

        StringValueParseException(String message) {
            super(message);
        }

        StringValueParseException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

