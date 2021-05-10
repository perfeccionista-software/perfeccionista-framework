package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementIsSelected extends Reason {

    static ElementIsSelectedAssertionError assertionError(@NotNull String message) {
        return new ElementIsSelectedAssertionError(message);
    }

    static ElementIsSelectedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementIsSelectedAssertionError(message, cause);
    }

    class ElementIsSelectedAssertionError extends PerfeccionistaAssertionError implements ElementIsSelected {

        ElementIsSelectedAssertionError(String message) {
            super(message);
        }

        ElementIsSelectedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
