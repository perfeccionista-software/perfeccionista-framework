package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FieldNotFound extends Reason {

    static FieldNotFoundException exception(@NotNull String message) {
        return new FieldNotFoundException(message);
    }

    static FieldNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FieldNotFoundException(message, cause);
    }

    class FieldNotFoundException extends PerfeccionistaRuntimeException implements FieldNotFound {

        FieldNotFoundException(String message) {
            super(message);
        }

        FieldNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

