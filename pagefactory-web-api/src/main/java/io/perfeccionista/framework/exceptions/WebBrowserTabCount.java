package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabCount extends Reason {

    static WebBrowserTabCountError assertionError(@NotNull String message) {
        return new WebBrowserTabCountError(message);
    }

    static WebBrowserTabCountError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserTabCountError(message, cause);
    }

    class WebBrowserTabCountError extends PerfeccionistaAssertionError implements WebBrowserTabCount {

        WebBrowserTabCountError(String message) {
            super(message);
        }

        WebBrowserTabCountError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
