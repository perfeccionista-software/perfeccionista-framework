package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface LocationFormatNotResolved extends Reason {

    static LocationFormatNotResolvedException exception(@NotNull String message) {
        return new LocationFormatNotResolvedException(message);
    }

    static LocationFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new LocationFormatNotResolvedException(message, cause);
    }

    class LocationFormatNotResolvedException extends PerfeccionistaRuntimeException implements LocationFormatNotResolved {

        LocationFormatNotResolvedException(String message) {
            super(message);
        }

        LocationFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
