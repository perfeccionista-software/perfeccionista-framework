package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotPresent extends Reason {

    static WebElementNotPresentAssertionError assertionError(@NotNull String message) {
        return new WebElementNotPresentAssertionError(message);
    }

    static WebElementNotPresentAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotPresentAssertionError(message, cause);
    }

    class WebElementNotPresentAssertionError extends PerfeccionistaAssertionError implements WebElementNotPresent {

        WebElementNotPresentAssertionError(String message) {
            super(message);
        }

        WebElementNotPresentAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

