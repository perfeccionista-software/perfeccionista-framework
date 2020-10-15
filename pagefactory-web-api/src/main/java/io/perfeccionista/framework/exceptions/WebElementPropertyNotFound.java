package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementPropertyNotFound extends Reason {

    static WebElementPropertyNotFoundException exception(@NotNull String message) {
        return new WebElementPropertyNotFoundException(message);
    }

    static WebElementPropertyNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementPropertyNotFoundException(message, cause);
    }

    class WebElementPropertyNotFoundException extends PerfeccionistaRuntimeException implements WebElementPropertyNotFound {

        WebElementPropertyNotFoundException(String message) {
            super(message);
        }

        WebElementPropertyNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
