package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface BigDecimalValueConditionNotResolved extends Reason {

    static BigDecimalValueConditionNotResolvedException exception(@NotNull String message) {
        return new BigDecimalValueConditionNotResolvedException(message);
    }

    static BigDecimalValueConditionNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new BigDecimalValueConditionNotResolvedException(message, cause);
    }

    class BigDecimalValueConditionNotResolvedException extends PerfeccionistaRuntimeException implements BigDecimalValueConditionNotResolved {

        BigDecimalValueConditionNotResolvedException(String message) {
            super(message);
        }

        BigDecimalValueConditionNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
