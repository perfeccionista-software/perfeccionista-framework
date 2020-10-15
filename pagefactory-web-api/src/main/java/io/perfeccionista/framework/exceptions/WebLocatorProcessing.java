package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebLocatorProcessing extends Reason {

    static WebLocatorProcessingException exception(@NotNull String message) {
        return new WebLocatorProcessingException(message);
    }

    static WebLocatorProcessingException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebLocatorProcessingException(message, cause);
    }

    class WebLocatorProcessingException extends PerfeccionistaRuntimeException implements WebLocatorProcessing {

        WebLocatorProcessingException(String message) {
            super(message);
        }

        WebLocatorProcessingException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
