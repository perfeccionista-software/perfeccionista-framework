package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserConfigurationNotFound extends Reason {

    static WebBrowserConfigurationNotFoundException exception(@NotNull String message) {
        return new WebBrowserConfigurationNotFoundException(message);
    }

    static WebBrowserConfigurationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserConfigurationNotFoundException(message, cause);
    }

    class WebBrowserConfigurationNotFoundException extends PerfeccionistaRuntimeException implements WebBrowserConfigurationNotFound {

        WebBrowserConfigurationNotFoundException(String message) {
            super(message);
        }

        WebBrowserConfigurationNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

