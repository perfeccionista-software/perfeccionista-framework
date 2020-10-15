package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotDisplayed extends Reason {

    static WebElementNotDisplayedAssertionError assertionError(@NotNull String message) {
        return new WebElementNotDisplayedAssertionError(message);
    }

    static WebElementNotDisplayedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotDisplayedAssertionError(message, cause);
    }

    static WebElementNotDisplayedException exception(@NotNull String message) {
        return new WebElementNotDisplayedException(message);
    }

    static WebElementNotDisplayedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotDisplayedException(message, cause);
    }

    class WebElementNotDisplayedAssertionError extends PerfeccionistaAssertionError implements WebElementNotDisplayed {

        WebElementNotDisplayedAssertionError(String message) {
            super(message);
        }

        WebElementNotDisplayedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class WebElementNotDisplayedException extends PerfeccionistaRuntimeException implements WebElementNotDisplayed {

        WebElementNotDisplayedException(String message) {
            super(message);
        }

        WebElementNotDisplayedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

