package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.SingleResultConversionException;
import org.jetbrains.annotations.NotNull;

public interface SingleResultConversion extends Reason {

    static SingleResultConversionException exception(@NotNull String message) {
        return new SingleResultConversionException(message);
    }

    static SingleResultConversionException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SingleResultConversionException(message, cause);
    }

}

