package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface UnsupportedScreenshotMimeType extends Reason {

    static UnsupportedScreenshotMimeTypeException exception(@NotNull String message) {
        return new UnsupportedScreenshotMimeTypeException(message);
    }

    static UnsupportedScreenshotMimeTypeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new UnsupportedScreenshotMimeTypeException(message, cause);
    }

    class UnsupportedScreenshotMimeTypeException extends PerfeccionistaRuntimeException implements UnsupportedScreenshotMimeType {

        UnsupportedScreenshotMimeTypeException(String message) {
            super(message);
        }

        UnsupportedScreenshotMimeTypeException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

