package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class EmptyElementPathException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public EmptyElementPathException(String message) {
        super(message);
    }

    public EmptyElementPathException(String message, Throwable cause) {
        super(message, cause);
    }

}
