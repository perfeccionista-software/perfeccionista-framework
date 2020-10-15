package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementImplementationNotFound extends Reason {

    static WebElementImplementationNotFoundException exception(@NotNull String message) {
        return new WebElementImplementationNotFoundException(message);
    }

    static WebElementImplementationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementImplementationNotFoundException(message, cause);
    }

    class WebElementImplementationNotFoundException extends PerfeccionistaRuntimeException implements WebElementImplementationNotFound {

        WebElementImplementationNotFoundException(String message) {
            super(message);
        }

        WebElementImplementationNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}