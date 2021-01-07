package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabNotFound extends Reason {

    static WebBrowserTabNotFoundException exception(@NotNull String message) {
        return new WebBrowserTabNotFoundException(message);
    }

    static WebBrowserTabNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserTabNotFoundException(message, cause);
    }

    class WebBrowserTabNotFoundException extends PerfeccionistaRuntimeException implements WebBrowserTabNotFound {

        WebBrowserTabNotFoundException(String message) {
            super(message);
        }

        WebBrowserTabNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

