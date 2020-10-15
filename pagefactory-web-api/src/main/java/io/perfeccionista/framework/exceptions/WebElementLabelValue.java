package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementLabelValue extends Reason {

    static WebElementLabelValueAssertionError assertionError(@NotNull String message) {
        return new WebElementLabelValueAssertionError(message);
    }

    static WebElementLabelValueAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementLabelValueAssertionError(message, cause);
    }

    class WebElementLabelValueAssertionError extends PerfeccionistaAssertionError implements WebElementLabelValue {

        WebElementLabelValueAssertionError(String message) {
            super(message);
        }

        WebElementLabelValueAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}


