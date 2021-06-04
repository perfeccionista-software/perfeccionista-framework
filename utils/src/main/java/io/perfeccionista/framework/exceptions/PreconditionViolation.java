package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface PreconditionViolation extends Reason {

    static PreconditionViolationException exception(@NotNull String message) {
        return new PreconditionViolationException(message);
    }

    static PreconditionViolationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new PreconditionViolationException(message, cause);
    }

    class PreconditionViolationException extends PerfeccionistaRuntimeException implements PreconditionViolation {

        PreconditionViolationException(String message) {
            super(message);
        }

        PreconditionViolationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

