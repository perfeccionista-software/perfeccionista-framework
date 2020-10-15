package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementOnTheScreen extends Reason {

    static WebElementOnTheScreenAssertionError assertionError(@NotNull String message) {
        return new WebElementOnTheScreenAssertionError(message);
    }

    static WebElementOnTheScreenAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementOnTheScreenAssertionError(message, cause);
    }

    class WebElementOnTheScreenAssertionError extends PerfeccionistaAssertionError implements WebElementOnTheScreen {

        WebElementOnTheScreenAssertionError(String message) {
            super(message);
        }

        WebElementOnTheScreenAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
