package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementActionNotFound extends Reason {

    static WebElementActionNotFoundException exception(@NotNull String message) {
        return new WebElementActionNotFoundException(message);
    }

    static WebElementActionNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementActionNotFoundException(message, cause);
    }

    class WebElementActionNotFoundException extends PerfeccionistaRuntimeException implements WebElementActionNotFound {

        WebElementActionNotFoundException(String message) {
            super(message);
        }

        WebElementActionNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

