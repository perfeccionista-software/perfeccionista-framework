package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ResponseFormatIsNotValid extends Reason {

    static ResponseFormatIsNotValidException exception(@NotNull String message) {
        return new ResponseFormatIsNotValidException(message);
    }

    static ResponseFormatIsNotValidException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ResponseFormatIsNotValidException(message, cause);
    }

    class ResponseFormatIsNotValidException extends PerfeccionistaRuntimeException implements ResponseFormatIsNotValid {

        ResponseFormatIsNotValidException(String message) {
            super(message);
        }

        ResponseFormatIsNotValidException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
