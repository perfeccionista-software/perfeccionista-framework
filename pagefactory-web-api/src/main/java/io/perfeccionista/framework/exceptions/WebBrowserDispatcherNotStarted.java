package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserDispatcherNotStarted extends Reason {

    static WebBrowserDispatcherNotStartedException exception(@NotNull String message) {
        return new WebBrowserDispatcherNotStartedException(message);
    }

    static WebBrowserDispatcherNotStartedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserDispatcherNotStartedException(message, cause);
    }

    class WebBrowserDispatcherNotStartedException extends PerfeccionistaRuntimeException implements WebBrowserDispatcherNotStarted {

        WebBrowserDispatcherNotStartedException(String message) {
            super(message);
        }

        WebBrowserDispatcherNotStartedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

