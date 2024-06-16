package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.UnsupportedScreenshotMimeTypeException;
import org.jetbrains.annotations.NotNull;

public interface UnsupportedScreenshotMimeType extends Reason {

    static UnsupportedScreenshotMimeTypeException exception(@NotNull String message) {
        return new UnsupportedScreenshotMimeTypeException(message);
    }

    static UnsupportedScreenshotMimeTypeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new UnsupportedScreenshotMimeTypeException(message, cause);
    }

}

