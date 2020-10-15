package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebLocatorNotFound extends Reason {

    static WebLocatorNotFoundException exception(@NotNull String message) {
        return new WebLocatorNotFoundException(message);
    }

    static WebLocatorNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebLocatorNotFoundException(message, cause);
    }

    class WebLocatorNotFoundException extends PerfeccionistaRuntimeException implements WebLocatorNotFound {

        WebLocatorNotFoundException(String message) {
            super(message);
        }

        WebLocatorNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}