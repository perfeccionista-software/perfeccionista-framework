package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementDimensionsException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementDimensionsException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementDimensionsException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}

