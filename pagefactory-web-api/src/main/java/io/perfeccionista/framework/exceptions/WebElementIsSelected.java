package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsSelected extends Reason {

    static WebElementIsSelectedAssertionError assertionError(@NotNull String message) {
        return new WebElementIsSelectedAssertionError(message);
    }

    static WebElementIsSelectedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsSelectedAssertionError(message, cause);
    }

    class WebElementIsSelectedAssertionError extends PerfeccionistaAssertionError implements WebElementIsSelected {

        WebElementIsSelectedAssertionError(String message) {
            super(message);
        }

        WebElementIsSelectedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
