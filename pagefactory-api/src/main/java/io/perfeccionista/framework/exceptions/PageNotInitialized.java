package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface PageNotInitialized extends Reason {

    static PageNotInitializedException exception(@NotNull String message) {
        return new PageNotInitializedException(message);
    }

    static PageNotInitializedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new PageNotInitializedException(message, cause);
    }

    class PageNotInitializedException extends PerfeccionistaRuntimeException implements PageNotInitialized {

        PageNotInitializedException(String message) {
            super(message);
        }

        PageNotInitializedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
