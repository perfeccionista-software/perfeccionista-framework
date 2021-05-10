package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotSelected extends Reason {

    static ElementNotSelectedAssertionError assertionError(@NotNull String message) {
        return new ElementNotSelectedAssertionError(message);
    }

    static ElementNotSelectedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotSelectedAssertionError(message, cause);
    }

    class ElementNotSelectedAssertionError extends PerfeccionistaAssertionError implements ElementNotSelected {

        ElementNotSelectedAssertionError(String message) {
            super(message);
        }

        ElementNotSelectedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

