package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface StateExtractorCreating extends Reason {

    static StateExtractorCreatingException exception(@NotNull String message) {
        return new StateExtractorCreatingException(message);
    }

    static StateExtractorCreatingException exception(@NotNull String message, @NotNull Throwable cause) {
        return new StateExtractorCreatingException(message, cause);
    }

    class StateExtractorCreatingException extends PerfeccionistaRuntimeException implements StateExtractorCreating {

        StateExtractorCreatingException(String message) {
            super(message);
        }

        StateExtractorCreatingException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
