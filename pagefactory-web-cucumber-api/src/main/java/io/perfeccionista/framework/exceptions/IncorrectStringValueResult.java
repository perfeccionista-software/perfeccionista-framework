package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IncorrectStringValueResult extends Reason {

    static IncorrectStringValueResultException exception(@NotNull String message) {
        return new IncorrectStringValueResultException(message);
    }

    static IncorrectStringValueResultException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectStringValueResultException(message, cause);
    }

    class IncorrectStringValueResultException extends PerfeccionistaRuntimeException implements IncorrectStringValueResult {

        IncorrectStringValueResultException(String message) {
            super(message);
        }

        IncorrectStringValueResultException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

