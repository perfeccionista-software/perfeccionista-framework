package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.IncorrectUrl;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class IncorrectUrlException extends PerfeccionistaRuntimeException implements IncorrectUrl {

    public IncorrectUrlException(String message) {
        super(message);
    }

    public IncorrectUrlException(String message, Throwable cause) {
        super(message, cause);
    }

}
