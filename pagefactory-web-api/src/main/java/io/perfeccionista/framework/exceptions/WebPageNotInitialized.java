package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebPageNotInitialized extends Reason {

    static WebPageNotInitializedException exception(@NotNull String message) {
        return new WebPageNotInitializedException(message);
    }

    static WebPageNotInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebPageNotInitializedException(message, cause);
    }

    class WebPageNotInitializedException extends PerfeccionistaRuntimeException implements WebPageNotInitialized {

        WebPageNotInitializedException(String message) {
            super(message);
        }

        WebPageNotInitializedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

