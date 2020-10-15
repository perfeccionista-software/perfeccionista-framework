package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumStaleWebElementReference extends Reason {

    static SeleniumStaleWebElementReferenceException exception(@NotNull String message) {
        return new SeleniumStaleWebElementReferenceException(message);
    }

    static SeleniumStaleWebElementReferenceException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumStaleWebElementReferenceException(message, cause);
    }

    class SeleniumStaleWebElementReferenceException extends PerfeccionistaRuntimeException implements SeleniumStaleWebElementReference {

        SeleniumStaleWebElementReferenceException(String message) {
            super(message);
        }

        SeleniumStaleWebElementReferenceException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

