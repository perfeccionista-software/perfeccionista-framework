package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebFilterConditionNotResolved extends Reason {

    static WebFilterConditionNotResolvedException exception(@NotNull String message) {
        return new WebFilterConditionNotResolvedException(message);
    }

    static WebFilterConditionNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebFilterConditionNotResolvedException(message, cause);
    }

    class WebFilterConditionNotResolvedException extends PerfeccionistaRuntimeException implements WebFilterConditionNotResolved {

        WebFilterConditionNotResolvedException(String message) {
            super(message);
        }

        WebFilterConditionNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
