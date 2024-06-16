package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.UrlReadingFailed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class UrlReadingFailedException extends PerfeccionistaRuntimeException implements UrlReadingFailed {

    public UrlReadingFailedException(String message) {
        super(message);
    }

    public UrlReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
