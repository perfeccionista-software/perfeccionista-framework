package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ExceptionMapperNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface ExceptionMapperNotFound extends Reason {

    static ExceptionMapperNotFoundException exception(@NotNull String message) {
        return new ExceptionMapperNotFoundException(message);
    }

    static ExceptionMapperNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ExceptionMapperNotFoundException(message, cause);
    }

}
