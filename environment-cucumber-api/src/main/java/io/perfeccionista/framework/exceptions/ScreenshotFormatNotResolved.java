package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ScreenshotFormatNotResolved extends Reason {

    static ScreenshotFormatNotResolvedException exception(@NotNull String message) {
        return new ScreenshotFormatNotResolvedException(message);
    }

    static ScreenshotFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ScreenshotFormatNotResolvedException(message, cause);
    }

    class ScreenshotFormatNotResolvedException extends PerfeccionistaRuntimeException implements ScreenshotFormatNotResolved {

        ScreenshotFormatNotResolvedException(String message) {
            super(message);
        }

        ScreenshotFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
