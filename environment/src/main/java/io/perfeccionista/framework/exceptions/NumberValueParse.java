package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.NumberValueParseException;
import org.jetbrains.annotations.NotNull;

public interface NumberValueParse extends Reason {

    static NumberValueParseException exception(@NotNull String message) {
        return new NumberValueParseException(message);
    }

    static NumberValueParseException exception(@NotNull String message, @NotNull Throwable cause) {
        return new NumberValueParseException(message, cause);
    }

}
