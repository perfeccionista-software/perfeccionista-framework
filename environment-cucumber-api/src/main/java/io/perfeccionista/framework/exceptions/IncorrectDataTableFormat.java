package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IncorrectDataTableFormat extends Reason {

    static IncorrectDataTableFormatException exception(@NotNull String message) {
        return new IncorrectDataTableFormatException(message);
    }

    static IncorrectDataTableFormatException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectDataTableFormatException(message, cause);
    }

    class IncorrectDataTableFormatException extends PerfeccionistaRuntimeException implements IncorrectDataTableFormat {

        IncorrectDataTableFormatException(String message) {
            super(message);
        }

        IncorrectDataTableFormatException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
