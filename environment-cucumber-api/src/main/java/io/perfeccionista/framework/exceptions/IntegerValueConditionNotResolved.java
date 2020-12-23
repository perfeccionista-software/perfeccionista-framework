package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IntegerValueConditionNotResolved extends Reason {

    static IntegerValueConditionNotResolvedException exception(@NotNull String message) {
        return new IntegerValueConditionNotResolvedException(message);
    }

    static IntegerValueConditionNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IntegerValueConditionNotResolvedException(message, cause);
    }

    class IntegerValueConditionNotResolvedException extends PerfeccionistaRuntimeException implements IntegerValueConditionNotResolved {

        IntegerValueConditionNotResolvedException(String message) {
            super(message);
        }

        IntegerValueConditionNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

