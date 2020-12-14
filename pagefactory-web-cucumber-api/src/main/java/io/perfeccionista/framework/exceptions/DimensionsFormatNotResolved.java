package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface DimensionsFormatNotResolved extends Reason {

    static DimensionsFormatNotResolvedException exception(@NotNull String message) {
        return new DimensionsFormatNotResolvedException(message);
    }

    static DimensionsFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new DimensionsFormatNotResolvedException(message, cause);
    }

    class DimensionsFormatNotResolvedException extends PerfeccionistaRuntimeException implements DimensionsFormatNotResolved {

        DimensionsFormatNotResolvedException(String message) {
            super(message);
        }

        DimensionsFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
