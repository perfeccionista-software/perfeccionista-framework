package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SortDirectionFormatNotResolved extends Reason {

    static SortDirectionFormatNotResolvedException exception(@NotNull String message) {
        return new SortDirectionFormatNotResolvedException(message);
    }

    static SortDirectionFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SortDirectionFormatNotResolvedException(message, cause);
    }

    class SortDirectionFormatNotResolvedException extends PerfeccionistaRuntimeException implements SortDirectionFormatNotResolved {

        SortDirectionFormatNotResolvedException(String message) {
            super(message);
        }

        SortDirectionFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
