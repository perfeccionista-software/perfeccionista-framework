package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementDoesNotHaveExpectedState extends Reason {

    static ElementDoesNotHaveExpectedStateAssertionError assertionError(@NotNull String message) {
        return new ElementDoesNotHaveExpectedStateAssertionError(message);
    }

    static ElementDoesNotHaveExpectedStateAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementDoesNotHaveExpectedStateAssertionError(message, cause);
    }

    class ElementDoesNotHaveExpectedStateAssertionError extends PerfeccionistaAssertionError implements ElementDoesNotHaveExpectedState {

        ElementDoesNotHaveExpectedStateAssertionError(String message) {
            super(message);
        }

        ElementDoesNotHaveExpectedStateAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
