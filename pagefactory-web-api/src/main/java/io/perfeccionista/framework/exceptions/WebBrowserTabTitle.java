package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabTitle extends Reason {

    static WebBrowserTabTitleAssertionError assertionError(@NotNull String message) {
        return new WebBrowserTabTitleAssertionError(message);
    }

    static WebBrowserTabTitleAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserTabTitleAssertionError(message, cause);
    }

    class WebBrowserTabTitleAssertionError extends PerfeccionistaAssertionError implements WebBrowserTabTitle {

        WebBrowserTabTitleAssertionError(String message) {
            super(message);
        }

        WebBrowserTabTitleAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
