package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementIsDisplayed extends Reason {

    static ElementIsDisplayedAssertionError assertionError(@NotNull String message) {
        return new ElementIsDisplayedAssertionError(message);
    }

    static ElementIsDisplayedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementIsDisplayedAssertionError(message, cause);
    }

    class ElementIsDisplayedAssertionError extends PerfeccionistaAssertionError implements ElementIsDisplayed {

        ElementIsDisplayedAssertionError(String message) {
            super(message);
        }

        ElementIsDisplayedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

