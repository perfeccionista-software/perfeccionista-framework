package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class UnsupportedScreenshotMimeTypeException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public UnsupportedScreenshotMimeTypeException(String message) {
        super(message);
    }

    public UnsupportedScreenshotMimeTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}

