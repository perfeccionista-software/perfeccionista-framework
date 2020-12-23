package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface RequiredArgumentNotFound extends Reason {

    static RequiredArgumentNotFoundException exception(@NotNull String message) {
        return new RequiredArgumentNotFoundException(message);
    }

    static RequiredArgumentNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RequiredArgumentNotFoundException(message, cause);
    }

    class RequiredArgumentNotFoundException extends PerfeccionistaRuntimeException implements RequiredArgumentNotFound {

        RequiredArgumentNotFoundException(String message) {
            super(message);
        }

        RequiredArgumentNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
