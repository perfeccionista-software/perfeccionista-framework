package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsEnabled extends Reason {

    static WebElementIsEnabledAssertionError assertionError(@NotNull String message) {
        return new WebElementIsEnabledAssertionError(message);
    }

    static WebElementIsEnabledAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsEnabledAssertionError(message, cause);
    }

    class WebElementIsEnabledAssertionError extends PerfeccionistaAssertionError implements WebElementIsEnabled {

        WebElementIsEnabledAssertionError(String message) {
            super(message);
        }

        WebElementIsEnabledAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

