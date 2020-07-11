package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementIsSelectedException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementIsSelectedException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementIsSelectedException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
