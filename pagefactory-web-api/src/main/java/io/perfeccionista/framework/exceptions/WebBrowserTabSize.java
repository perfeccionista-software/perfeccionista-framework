package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabSize extends Reason {

    static WebBrowserTabSizeException exception(@NotNull String message) {
        return new WebBrowserTabSizeException(message);
    }

    static WebBrowserTabSizeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserTabSizeException(message, cause);
    }

    class WebBrowserTabSizeException extends PerfeccionistaRuntimeException implements WebBrowserTabSize {

        WebBrowserTabSizeException(String message) {
            super(message);
        }

        WebBrowserTabSizeException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
