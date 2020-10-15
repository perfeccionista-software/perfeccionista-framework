package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsPresent extends Reason {

    static WebElementIsPresentAssertionError assertionError(@NotNull String message) {
        return new WebElementIsPresentAssertionError(message);
    }

    static WebElementIsPresentAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsPresentAssertionError(message, cause);
    }

    class WebElementIsPresentAssertionError extends PerfeccionistaAssertionError implements WebElementIsPresent {

        WebElementIsPresentAssertionError(String message) {
            super(message);
        }

        WebElementIsPresentAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

