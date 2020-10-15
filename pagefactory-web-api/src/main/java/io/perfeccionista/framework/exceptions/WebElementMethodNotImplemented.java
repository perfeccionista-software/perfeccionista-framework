package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementMethodNotImplemented extends Reason {

    static WebElementMethodNotImplementedException exception(@NotNull String message) {
        return new WebElementMethodNotImplementedException(message);
    }

    static WebElementMethodNotImplementedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementMethodNotImplementedException(message, cause);
    }

    class WebElementMethodNotImplementedException extends PerfeccionistaRuntimeException implements WebElementMethodNotImplemented {

        WebElementMethodNotImplementedException(String message) {
            super(message);
        }

        WebElementMethodNotImplementedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

