package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebFilterOperatorNotResolved extends Reason {

    static WebFilterOperatorNotResolvedException exception(@NotNull String message) {
        return new WebFilterOperatorNotResolvedException(message);
    }

    static WebFilterOperatorNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebFilterOperatorNotResolvedException(message, cause);
    }

    class WebFilterOperatorNotResolvedException extends PerfeccionistaRuntimeException implements WebFilterOperatorNotResolved {

        WebFilterOperatorNotResolvedException(String message) {
            super(message);
        }

        WebFilterOperatorNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}