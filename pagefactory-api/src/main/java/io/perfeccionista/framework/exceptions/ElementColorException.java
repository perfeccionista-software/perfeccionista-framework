package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementColorException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementColorException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementColorException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
