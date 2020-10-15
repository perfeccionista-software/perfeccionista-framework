package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotFound extends Reason {

    static WebElementNotFoundException exception(@NotNull String message) {
        return new WebElementNotFoundException(message);
    }

    static WebElementNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotFoundException(message, cause);
    }

    class WebElementNotFoundException extends PerfeccionistaRuntimeException implements WebElementNotFound {

        WebElementNotFoundException(String message) {
            super(message);
        }

        WebElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

