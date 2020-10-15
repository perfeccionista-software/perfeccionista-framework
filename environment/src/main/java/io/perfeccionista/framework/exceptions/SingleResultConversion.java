package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SingleResultConversion extends Reason {

    static SingleResultConversionException exception(@NotNull String message) {
        return new SingleResultConversionException(message);
    }

    static SingleResultConversionException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SingleResultConversionException(message, cause);
    }

    class SingleResultConversionException extends PerfeccionistaRuntimeException implements SingleResultConversion {

        SingleResultConversionException(String message) {
            super(message);
        }

        SingleResultConversionException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

