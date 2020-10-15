package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementSearch extends Reason {

    static WebElementSearchException exception(@NotNull String message) {
        return new WebElementSearchException(message);
    }

    static WebElementSearchException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementSearchException(message, cause);
    }

    class WebElementSearchException extends PerfeccionistaRuntimeException implements WebElementSearch {

        WebElementSearchException(String message) {
            super(message);
        }

        WebElementSearchException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}