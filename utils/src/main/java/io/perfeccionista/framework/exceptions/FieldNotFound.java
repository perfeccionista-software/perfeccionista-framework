package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FieldNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface FieldNotFound extends Reason {

    static FieldNotFoundException exception(@NotNull String message) {
        return new FieldNotFoundException(message);
    }

    static FieldNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FieldNotFoundException(message, cause);
    }

}

