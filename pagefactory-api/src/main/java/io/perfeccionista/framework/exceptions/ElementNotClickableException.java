package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotClickableException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotClickableException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementNotClickableException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}

