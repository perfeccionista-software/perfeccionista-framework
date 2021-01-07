package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface CookieValidation extends Reason {

    static CookieValidationException exception(@NotNull String message) {
        return new CookieValidationException(message);
    }

    static CookieValidationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new CookieValidationException(message, cause);
    }

    class CookieValidationException extends PerfeccionistaRuntimeException implements CookieValidation {

        CookieValidationException(String message) {
            super(message);
        }

        CookieValidationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
