package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementIndex extends Reason {

    static WebElementIndexAssertionError assertionError(@NotNull String message) {
        return new WebElementIndexAssertionError(message);
    }

    static WebElementIndexAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementIndexAssertionError(message, cause);
    }

    class WebElementIndexAssertionError extends PerfeccionistaAssertionError implements WebElementIndex {

        WebElementIndexAssertionError(String message) {
            super(message);
        }

        WebElementIndexAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
