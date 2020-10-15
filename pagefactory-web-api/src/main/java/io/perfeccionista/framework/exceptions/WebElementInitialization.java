package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementInitialization extends Reason {

    static WebElementInitializationException exception(@NotNull String message) {
        return new WebElementInitializationException(message);
    }

    static WebElementInitializationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementInitializationException(message, cause);
    }

    class WebElementInitializationException extends PerfeccionistaRuntimeException implements WebElementInitialization {

        WebElementInitializationException(String message) {
            super(message);
        }

        WebElementInitializationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
