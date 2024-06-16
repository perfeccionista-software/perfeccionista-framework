package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.StringValueParseException;
import org.jetbrains.annotations.NotNull;

public interface StringValueParse extends Reason {

    static StringValueParseException exception(@NotNull String message) {
        return new StringValueParseException(message);
    }

    static StringValueParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new StringValueParseException(message, cause);
    }

}

