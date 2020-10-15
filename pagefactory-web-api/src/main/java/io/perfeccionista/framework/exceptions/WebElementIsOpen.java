package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIsOpen extends Reason {

    static WebElementIsOpenAssertionError assertionError(@NotNull String message) {
        return new WebElementIsOpenAssertionError(message);
    }

    static WebElementIsOpenAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIsOpenAssertionError(message, cause);
    }

    class WebElementIsOpenAssertionError extends PerfeccionistaAssertionError implements WebElementIsOpen {

        WebElementIsOpenAssertionError(String message) {
            super(message);
        }

        WebElementIsOpenAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
