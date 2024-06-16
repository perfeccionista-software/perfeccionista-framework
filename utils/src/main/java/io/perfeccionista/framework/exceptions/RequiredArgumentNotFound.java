package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.RequiredArgumentNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface RequiredArgumentNotFound extends Reason {

    static RequiredArgumentNotFoundException exception(@NotNull String message) {
        return new RequiredArgumentNotFoundException(message);
    }

    static RequiredArgumentNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RequiredArgumentNotFoundException(message, cause);
    }

}
