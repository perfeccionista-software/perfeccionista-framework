package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FieldAccessException;
import org.jetbrains.annotations.NotNull;

public interface FieldAccess extends Reason {

    static FieldAccessException exception(@NotNull String message) {
        return new FieldAccessException(message);
    }

    static FieldAccessException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FieldAccessException(message, cause);
    }

}
