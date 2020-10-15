package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsonParse extends Reason {

    static JsonParseException exception(@NotNull String message) {
        return new JsonParseException(message);
    }

    static JsonParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsonParseException(message, cause);
    }

    class JsonParseException extends PerfeccionistaRuntimeException implements JsonParse {

        JsonParseException(String message) {
            super(message);
        }

        JsonParseException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
