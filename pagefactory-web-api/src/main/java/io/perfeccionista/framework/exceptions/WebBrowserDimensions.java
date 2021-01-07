package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserDimensions extends Reason {

    static WebBrowserDimensionsException exception(@NotNull String message) {
        return new WebBrowserDimensionsException(message);
    }

    static WebBrowserDimensionsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserDimensionsException(message, cause);
    }

    class WebBrowserDimensionsException extends PerfeccionistaRuntimeException implements WebBrowserDimensions {

        WebBrowserDimensionsException(String message) {
            super(message);
        }

        WebBrowserDimensionsException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
