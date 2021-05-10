package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementIsPresent extends Reason {

    static ElementIsPresentAssertionError assertionError(@NotNull String message) {
        return new ElementIsPresentAssertionError(message);
    }

    static ElementIsPresentAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementIsPresentAssertionError(message, cause);
    }

    class ElementIsPresentAssertionError extends PerfeccionistaAssertionError implements ElementIsPresent {

        ElementIsPresentAssertionError(String message) {
            super(message);
        }

        ElementIsPresentAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

