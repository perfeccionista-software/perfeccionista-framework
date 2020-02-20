package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementSearchException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementSearchException(String message) {
        super(message);
    }

    public ElementSearchException(String message, Throwable cause) {
        super(message, cause);
    }

}