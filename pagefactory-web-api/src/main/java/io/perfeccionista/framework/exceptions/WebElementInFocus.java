package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementInFocus extends Reason {

    static WebElementInFocusAssertionError assertionError(@NotNull String message) {
        return new WebElementInFocusAssertionError(message);
    }

    static WebElementInFocusAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementInFocusAssertionError(message, cause);
    }

    class WebElementInFocusAssertionError extends PerfeccionistaAssertionError implements WebElementInFocus {

        WebElementInFocusAssertionError(String message) {
            super(message);
        }

        WebElementInFocusAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
