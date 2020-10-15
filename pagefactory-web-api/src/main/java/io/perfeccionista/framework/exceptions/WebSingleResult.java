package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebSingleResult extends Reason {

    static WebSingleResultException exception(@NotNull String message) {
        return new WebSingleResultException(message);
    }

    static WebSingleResultException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebSingleResultException(message, cause);
    }

    class WebSingleResultException extends PerfeccionistaRuntimeException implements WebSingleResult {

        WebSingleResultException(String message) {
            super(message);
        }

        WebSingleResultException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
