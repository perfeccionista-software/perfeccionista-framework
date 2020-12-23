package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ColorFormatNotResolved extends Reason {

    static ColorFormatNotResolvedException exception(@NotNull String message) {
        return new ColorFormatNotResolvedException(message);
    }

    static ColorFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ColorFormatNotResolvedException(message, cause);
    }

    class ColorFormatNotResolvedException extends PerfeccionistaRuntimeException implements ColorFormatNotResolved {

        ColorFormatNotResolvedException(String message) {
            super(message);
        }

        ColorFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
