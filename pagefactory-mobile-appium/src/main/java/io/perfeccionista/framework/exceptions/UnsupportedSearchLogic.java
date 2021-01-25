package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface UnsupportedSearchLogic extends Reason {

    static UnsupportedSearchLogicException exception(@NotNull String message) {
        return new UnsupportedSearchLogicException(message);
    }

    static UnsupportedSearchLogicException exception(@NotNull String message, @NotNull Throwable cause) {
        return new UnsupportedSearchLogicException(message, cause);
    }

    class UnsupportedSearchLogicException extends PerfeccionistaRuntimeException implements UnsupportedSearchLogic {

        UnsupportedSearchLogicException(String message) {
            super(message);
        }

        UnsupportedSearchLogicException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
