package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IncorrectIntegerValueResult extends Reason {

    static IncorrectIntegerValueResultException exception(@NotNull String message) {
        return new IncorrectIntegerValueResultException(message);
    }

    static IncorrectIntegerValueResultException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectIntegerValueResultException(message, cause);
    }

    class IncorrectIntegerValueResultException extends PerfeccionistaRuntimeException implements IncorrectIntegerValueResult {

        IncorrectIntegerValueResultException(String message) {
            super(message);
        }

        IncorrectIntegerValueResultException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
