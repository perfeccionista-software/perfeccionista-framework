package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementColor extends Reason {

    static WebElementColorAssertionError assertionError(@NotNull String message) {
        return new WebElementColorAssertionError(message);
    }

    static WebElementColorAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementColorAssertionError(message, cause);
    }

    class WebElementColorAssertionError extends PerfeccionistaAssertionError implements WebElementColor {

        WebElementColorAssertionError(String message) {
            super(message);
        }

        WebElementColorAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
