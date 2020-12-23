package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface StringValueConditionNotResolved extends Reason {

    static StringValueConditionNotResolvedException exception(@NotNull String message) {
        return new StringValueConditionNotResolvedException(message);
    }

    static StringValueConditionNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new StringValueConditionNotResolvedException(message, cause);
    }

    class StringValueConditionNotResolvedException extends PerfeccionistaRuntimeException implements StringValueConditionNotResolved {

        StringValueConditionNotResolvedException(String message) {
            super(message);
        }

        StringValueConditionNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

