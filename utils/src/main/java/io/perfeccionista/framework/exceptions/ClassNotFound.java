package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ClassNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface ClassNotFound extends Reason {

    static ClassNotFoundException exception(@NotNull String message) {
        return new ClassNotFoundException(message);
    }

    static ClassNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassNotFoundException(message, cause);
    }

}
