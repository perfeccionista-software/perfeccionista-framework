package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNameNotFound extends Reason {

    static WebElementNameNotFoundException exception(@NotNull String message) {
        return new WebElementNameNotFoundException(message);
    }

    static WebElementNameNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNameNotFoundException(message, cause);
    }

    class WebElementNameNotFoundException extends PerfeccionistaRuntimeException implements WebElementNameNotFound {

        WebElementNameNotFoundException(String message) {
            super(message);
        }

        WebElementNameNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}


