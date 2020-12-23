package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface DurationFormatNotResolved extends Reason {

    static DurationFormatNotResolvedException exception(@NotNull String message) {
        return new DurationFormatNotResolvedException(message);
    }

    static DurationFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new DurationFormatNotResolvedException(message, cause);
    }

    class DurationFormatNotResolvedException extends PerfeccionistaRuntimeException implements DurationFormatNotResolved {

        DurationFormatNotResolvedException(String message) {
            super(message);
        }

        DurationFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
