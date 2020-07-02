package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementImplementationException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementImplementationException(String message) {
        super(message);
    }

    public ElementImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

}

