package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.PreconditionViolationException;
import org.jetbrains.annotations.NotNull;

public interface PreconditionViolation extends Reason {

    static PreconditionViolationException exception(@NotNull String message) {
        return new PreconditionViolationException(message);
    }

    static PreconditionViolationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new PreconditionViolationException(message, cause);
    }

}

