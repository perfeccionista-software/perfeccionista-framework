package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementSizeException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementSizeException(String message) {
        super(message);
    }

    public ElementSizeException(String message, Throwable cause) {
        super(message, cause);
    }

}
