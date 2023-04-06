package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementAttributeValue extends Reason {

    static WebElementAttributeValueAssertionError assertionError(@NotNull String message) {
        return new WebElementAttributeValueAssertionError(message);
    }

    static WebElementAttributeValueAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementAttributeValueAssertionError(message, cause);
    }

    class WebElementAttributeValueAssertionError extends PerfeccionistaAssertionError implements WebElementAttributeValue {

        WebElementAttributeValueAssertionError(String message) {
            super(message);
        }

        WebElementAttributeValueAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
