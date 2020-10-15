package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementCast extends Reason {

    static WebElementCastException exception(@NotNull String message) {
        return new WebElementCastException(message);
    }

    static WebElementCastException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementCastException(message, cause);
    }

    class WebElementCastException extends PerfeccionistaRuntimeException implements WebElementCast {

        WebElementCastException(String message) {
            super(message);
        }

        WebElementCastException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
