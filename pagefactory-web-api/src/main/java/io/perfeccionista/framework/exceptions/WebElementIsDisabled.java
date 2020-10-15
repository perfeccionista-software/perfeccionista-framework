package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsDisabled extends Reason {

    static WebElementIsDisabledAssertionError assertionError(@NotNull String message) {
        return new WebElementIsDisabledAssertionError(message);
    }

    static WebElementIsDisabledAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsDisabledAssertionError(message, cause);
    }

    static WebElementIsDisabledException exception(@NotNull String message) {
        return new WebElementIsDisabledException(message);
    }

    static WebElementIsDisabledException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsDisabledException(message, cause);
    }

    class WebElementIsDisabledAssertionError extends PerfeccionistaAssertionError implements WebElementIsDisabled {

        WebElementIsDisabledAssertionError(String message) {
            super(message);
        }

        WebElementIsDisabledAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class WebElementIsDisabledException extends PerfeccionistaRuntimeException implements WebElementIsDisabled {

        WebElementIsDisabledException(String message) {
            super(message);
        }

        WebElementIsDisabledException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
