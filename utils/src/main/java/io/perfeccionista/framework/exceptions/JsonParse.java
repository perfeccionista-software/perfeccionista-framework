package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.JsonParseException;
import org.jetbrains.annotations.NotNull;

public interface JsonParse extends Reason {

    static JsonParseException exception(@NotNull String message) {
        return new JsonParseException(message);
    }

    static JsonParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsonParseException(message, cause);
    }

}
