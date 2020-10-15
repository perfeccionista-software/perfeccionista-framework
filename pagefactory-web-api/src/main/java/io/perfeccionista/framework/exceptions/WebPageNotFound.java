package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebPageNotFound extends Reason {

    static WebPageNotFoundException exception(@NotNull String message) {
        return new WebPageNotFoundException(message);
    }

    static WebPageNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebPageNotFoundException(message, cause);
    }

    class WebPageNotFoundException extends PerfeccionistaRuntimeException implements WebPageNotFound {

        WebPageNotFoundException(String message) {
            super(message);
        }

        WebPageNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
