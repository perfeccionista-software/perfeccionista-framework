package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementInteractionNotFound extends Reason {

    static WebElementInteractionNotFoundException exception(@NotNull String message) {
        return new WebElementInteractionNotFoundException(message);
    }

    static WebElementInteractionNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementInteractionNotFoundException(message, cause);
    }

    class WebElementInteractionNotFoundException extends PerfeccionistaRuntimeException implements WebElementInteractionNotFound {

        WebElementInteractionNotFoundException(String message) {
            super(message);
        }

        WebElementInteractionNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
