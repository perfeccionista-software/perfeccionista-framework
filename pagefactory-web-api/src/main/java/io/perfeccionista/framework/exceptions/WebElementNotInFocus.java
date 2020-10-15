package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotInFocus extends Reason {

    static WebElementNotInFocusAssertionError assertionError(@NotNull String message) {
        return new WebElementNotInFocusAssertionError(message);
    }

    static WebElementNotInFocusAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotInFocusAssertionError(message, cause);
    }

    class WebElementNotInFocusAssertionError extends PerfeccionistaAssertionError implements WebElementNotInFocus {

        WebElementNotInFocusAssertionError(String message) {
            super(message);
        }

        WebElementNotInFocusAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}


