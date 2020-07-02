package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementIsDisplayedException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementIsDisplayedException(String message) {
        super(message);
    }

    public ElementIsDisplayedException(String message, Throwable cause) {
        super(message, cause);
    }

}

