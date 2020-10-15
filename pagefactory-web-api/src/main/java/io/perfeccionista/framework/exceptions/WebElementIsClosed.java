package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsClosed extends Reason {

    static WebElementIsClosedAssertionError assertionError(@NotNull String message) {
        return new WebElementIsClosedAssertionError(message);
    }

    static WebElementIsClosedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsClosedAssertionError(message, cause);
    }

    class WebElementIsClosedAssertionError extends PerfeccionistaAssertionError implements WebElementIsClosed {

        WebElementIsClosedAssertionError(String message) {
            super(message);
        }

        WebElementIsClosedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
