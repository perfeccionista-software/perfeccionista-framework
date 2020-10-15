package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementDimensions extends Reason {

    static WebElementDimensionsAssertionError assertionError(@NotNull String message) {
        return new WebElementDimensionsAssertionError(message);
    }

    static WebElementDimensionsAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementDimensionsAssertionError(message, cause);
    }

    class WebElementDimensionsAssertionError extends PerfeccionistaAssertionError implements WebElementDimensions {

        WebElementDimensionsAssertionError(String message) {
            super(message);
        }

        WebElementDimensionsAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

