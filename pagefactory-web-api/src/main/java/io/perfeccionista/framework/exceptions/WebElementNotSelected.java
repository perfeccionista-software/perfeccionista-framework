package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotSelected extends Reason {

    static WebElementNotSelectedAssertionError assertionError(@NotNull String message) {
        return new WebElementNotSelectedAssertionError(message);
    }

    static WebElementNotSelectedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotSelectedAssertionError(message, cause);
    }

    class WebElementNotSelectedAssertionError extends PerfeccionistaAssertionError implements WebElementNotSelected {

        WebElementNotSelectedAssertionError(String message) {
            super(message);
        }

        WebElementNotSelectedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

