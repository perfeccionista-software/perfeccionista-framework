package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SingleResultCreating extends Reason {

    static SingleResultCreatingException exception(@NotNull String message) {
        return new SingleResultCreatingException(message);
    }

    static SingleResultCreatingException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SingleResultCreatingException(message, cause);
    }

    class SingleResultCreatingException extends PerfeccionistaRuntimeException implements SingleResultCreating {

        SingleResultCreatingException(String message) {
            super(message);
        }

        SingleResultCreatingException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
