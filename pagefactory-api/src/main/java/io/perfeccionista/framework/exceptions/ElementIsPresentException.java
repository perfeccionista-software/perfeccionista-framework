package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementIsPresentException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementIsPresentException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementIsPresentException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
