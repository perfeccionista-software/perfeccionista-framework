package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface UrlReadingFailed extends Reason {

    static UrlReadingFailedException exception(@NotNull String message) {
        return new UrlReadingFailedException(message);
    }

    static UrlReadingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new UrlReadingFailedException(message, cause);
    }

    class UrlReadingFailedException extends PerfeccionistaRuntimeException implements UrlReadingFailed {

        UrlReadingFailedException(String message) {
            super(message);
        }

        UrlReadingFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
