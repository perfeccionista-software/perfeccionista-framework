package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementHasUnexpectedState extends Reason {

    static ElementHasUnexpectedStateAssertionError assertionError(@NotNull String message) {
        return new ElementHasUnexpectedStateAssertionError(message);
    }

    static ElementHasUnexpectedStateAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementHasUnexpectedStateAssertionError(message, cause);
    }

    class ElementHasUnexpectedStateAssertionError extends PerfeccionistaAssertionError implements ElementHasUnexpectedState {

        ElementHasUnexpectedStateAssertionError(String message) {
            super(message);
        }

        ElementHasUnexpectedStateAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
