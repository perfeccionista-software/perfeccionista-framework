package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotPresent extends Reason {

    static ElementNotPresentException exception(@NotNull String message) {
        return new ElementNotPresentException(message);
    }

    static ElementNotPresentException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotPresentException(message, cause);
    }

    static ElementNotPresentAssertionError assertionError(@NotNull String message) {
        return new ElementNotPresentAssertionError(message);
    }

    static ElementNotPresentAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotPresentAssertionError(message, cause);
    }

    class ElementNotPresentException extends PerfeccionistaRuntimeException implements ElementNotPresent {

        public ElementNotPresentException(@NotNull String message) {
            super(message);
        }

        public ElementNotPresentException(@NotNull String message, @NotNull Throwable cause) {
            super(message, cause);
        }
    }

    class ElementNotPresentAssertionError extends PerfeccionistaAssertionError implements ElementNotPresent {

        ElementNotPresentAssertionError(String message) {
            super(message);
        }

        ElementNotPresentAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
