package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementPropertyValue extends Reason {

    static WebElementPropertyValueAssertionError assertionError(@NotNull String message) {
        return new WebElementPropertyValueAssertionError(message);
    }

    static WebElementPropertyValueAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementPropertyValueAssertionError(message, cause);
    }

    class WebElementPropertyValueAssertionError extends PerfeccionistaAssertionError implements WebElementPropertyValue {

        WebElementPropertyValueAssertionError(String message) {
            super(message);
        }

        WebElementPropertyValueAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
