package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementNotClickable extends Reason {

    static WebElementNotClickableException exception(@NotNull String message) {
        return new WebElementNotClickableException(message);
    }

    static WebElementNotClickableException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementNotClickableException(message, cause);
    }

    class WebElementNotClickableException extends PerfeccionistaRuntimeException implements WebElementNotClickable {

        WebElementNotClickableException(String message) {
            super(message);
        }

        WebElementNotClickableException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
