package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotOnTheScreen extends Reason {

    static WebElementNotOnTheScreenAssertionError assertionError(@NotNull String message) {
        return new WebElementNotOnTheScreenAssertionError(message);
    }

    static WebElementNotOnTheScreenAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotOnTheScreenAssertionError(message, cause);
    }

    class WebElementNotOnTheScreenAssertionError extends PerfeccionistaAssertionError implements WebElementNotOnTheScreen {

        WebElementNotOnTheScreenAssertionError(String message) {
            super(message);
        }

        WebElementNotOnTheScreenAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
