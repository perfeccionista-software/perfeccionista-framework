package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementTextValue extends Reason {

    static WebElementTextValueAssertionError assertionError(@NotNull String message) {
        return new WebElementTextValueAssertionError(message);
    }

    static WebElementTextValueAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementTextValueAssertionError(message, cause);
    }

    class WebElementTextValueAssertionError extends PerfeccionistaAssertionError implements WebElementTextValue {

        WebElementTextValueAssertionError(String message) {
            super(message);
        }

        WebElementTextValueAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
