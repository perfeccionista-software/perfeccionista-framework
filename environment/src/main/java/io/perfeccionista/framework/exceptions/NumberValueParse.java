package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface NumberValueParse extends Reason {

    static NumberValueParseException exception(@NotNull String message) {
        return new NumberValueParseException(message);
    }

    static NumberValueParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new NumberValueParseException(message, cause);
    }

    class NumberValueParseException extends PerfeccionistaRuntimeException implements NumberValueParse {

        NumberValueParseException(String message) {
            super(message);
        }

        NumberValueParseException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
