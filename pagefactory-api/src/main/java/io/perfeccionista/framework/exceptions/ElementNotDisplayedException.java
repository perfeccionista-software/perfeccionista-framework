package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotDisplayedException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotDisplayedException(String message) {
        super(message);
    }

    public ElementNotDisplayedException(String message, Throwable cause) {
        super(message, cause);
    }

}

