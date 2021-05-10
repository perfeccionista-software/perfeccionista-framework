package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface LocatorProcessing extends Reason {

    static LocatorProcessingException exception(@NotNull String message) {
        return new LocatorProcessingException(message);
    }

    static LocatorProcessingException exception(@NotNull String message, @NotNull Throwable cause) {
        return new LocatorProcessingException(message, cause);
    }

    class LocatorProcessingException extends PerfeccionistaRuntimeException implements LocatorProcessing {

        LocatorProcessingException(String message) {
            super(message);
        }

        LocatorProcessingException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
