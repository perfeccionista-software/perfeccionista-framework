package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementScreenshotException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementScreenshotException(String message) {
        super(message);
    }

    public ElementScreenshotException(String message, Throwable cause) {
        super(message, cause);
    }

}
