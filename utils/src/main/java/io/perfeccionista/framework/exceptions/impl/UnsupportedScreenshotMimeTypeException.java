package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.UnsupportedScreenshotMimeType;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class UnsupportedScreenshotMimeTypeException extends PerfeccionistaRuntimeException implements UnsupportedScreenshotMimeType {

    public UnsupportedScreenshotMimeTypeException(String message) {
        super(message);
    }

    public UnsupportedScreenshotMimeTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
