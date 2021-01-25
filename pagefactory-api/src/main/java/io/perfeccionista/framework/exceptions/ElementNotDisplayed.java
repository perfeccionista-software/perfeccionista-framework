package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotDisplayed extends Reason {

    static ElementNotDisplayedAssertionError assertionError(@NotNull String message) {
        return new ElementNotDisplayedAssertionError(message);
    }

    static ElementNotDisplayedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotDisplayedAssertionError(message, cause);
    }

    static ElementNotDisplayedException exception(@NotNull String message) {
        return new ElementNotDisplayedException(message);
    }

    static ElementNotDisplayedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotDisplayedException(message, cause);
    }

    class ElementNotDisplayedAssertionError extends PerfeccionistaAssertionError implements ElementNotDisplayed {

        ElementNotDisplayedAssertionError(String message) {
            super(message);
        }

        ElementNotDisplayedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class ElementNotDisplayedException extends PerfeccionistaRuntimeException implements ElementNotDisplayed {

        ElementNotDisplayedException(String message) {
            super(message);
        }

        ElementNotDisplayedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

