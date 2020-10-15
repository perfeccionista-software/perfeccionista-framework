package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementSize extends Reason {

    static WebElementSizeAssertionError assertionError(@NotNull String message) {
        return new WebElementSizeAssertionError(message);
    }

    static WebElementSizeAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementSizeAssertionError(message, cause);
    }

    class WebElementSizeAssertionError extends PerfeccionistaAssertionError implements WebElementSize {

        WebElementSizeAssertionError(String message) {
            super(message);
        }

        WebElementSizeAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}