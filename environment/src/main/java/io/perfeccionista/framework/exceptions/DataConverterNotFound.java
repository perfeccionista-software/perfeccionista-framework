package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface DataConverterNotFound extends Reason {

    static DataConverterNotFoundException exception(@NotNull String message) {
        return new DataConverterNotFoundException(message);
    }

    static DataConverterNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new DataConverterNotFoundException(message, cause);
    }

    class DataConverterNotFoundException extends PerfeccionistaRuntimeException implements DataConverterNotFound {

        DataConverterNotFoundException(String message) {
            super(message);
        }

        DataConverterNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
