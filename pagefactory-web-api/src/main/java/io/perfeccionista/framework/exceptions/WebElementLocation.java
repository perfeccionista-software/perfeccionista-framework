package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementLocation extends Reason {

    static WebElementLocationAssertionError assertionError(@NotNull String message) {
        return new WebElementLocationAssertionError(message);
    }

    static WebElementLocationAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementLocationAssertionError(message, cause);
    }

    class WebElementLocationAssertionError extends PerfeccionistaAssertionError implements WebElementLocation {

        WebElementLocationAssertionError(String message) {
            super(message);
        }

        WebElementLocationAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
