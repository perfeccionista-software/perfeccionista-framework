package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ValueNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface ValueNotFound extends Reason {

    static ValueNotFoundException exception(@NotNull String message) {
        return new ValueNotFoundException(message);
    }

    static ValueNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ValueNotFoundException(message, cause);
    }

}
