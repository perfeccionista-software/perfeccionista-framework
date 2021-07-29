package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IncorrectFileName extends Reason {

    static IncorrectFileNameException exception(@NotNull String message) {
        return new IncorrectFileNameException(message);
    }

    static IncorrectFileNameException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectFileNameException(message, cause);
    }

    class IncorrectFileNameException extends PerfeccionistaRuntimeException implements IncorrectFileName {

        IncorrectFileNameException(String message) {
            super(message);
        }

        IncorrectFileNameException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
