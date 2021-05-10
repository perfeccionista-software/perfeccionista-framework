package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotOnTheScreen extends Reason {

    static ElementNotOnTheScreenAssertionError assertionError(@NotNull String message) {
        return new ElementNotOnTheScreenAssertionError(message);
    }

    static ElementNotOnTheScreenAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotOnTheScreenAssertionError(message, cause);
    }

    static ElementNotOnTheScreenException exception(@NotNull String message) {
        return new ElementNotOnTheScreenException(message);
    }

    static ElementNotOnTheScreenException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotOnTheScreenException(message, cause);
    }


    class ElementNotOnTheScreenAssertionError extends PerfeccionistaAssertionError implements ElementNotOnTheScreen {

        ElementNotOnTheScreenAssertionError(String message) {
            super(message);
        }

        ElementNotOnTheScreenAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }


    class ElementNotOnTheScreenException extends PerfeccionistaRuntimeException implements ElementNotOnTheScreen {

        ElementNotOnTheScreenException(String message) {
            super(message);
        }

        ElementNotOnTheScreenException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
