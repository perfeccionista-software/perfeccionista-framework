package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsDisplayed extends Reason {

    static WebElementIsDisplayedAssertionError assertionError(@NotNull String message) {
        return new WebElementIsDisplayedAssertionError(message);
    }

    static WebElementIsDisplayedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsDisplayedAssertionError(message, cause);
    }

    class WebElementIsDisplayedAssertionError extends PerfeccionistaAssertionError implements WebElementIsDisplayed {

        WebElementIsDisplayedAssertionError(String message) {
            super(message);
        }

        WebElementIsDisplayedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

