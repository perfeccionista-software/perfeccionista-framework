package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabUrl extends Reason {

    static WebBrowserTabUrlAssertionError assertionError(@NotNull String message) {
        return new WebBrowserTabUrlAssertionError(message);
    }

    static WebBrowserTabUrlAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserTabUrlAssertionError(message, cause);
    }

    class WebBrowserTabUrlAssertionError extends PerfeccionistaAssertionError implements WebBrowserTabUrl {

        WebBrowserTabUrlAssertionError(String message) {
            super(message);
        }

        WebBrowserTabUrlAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
