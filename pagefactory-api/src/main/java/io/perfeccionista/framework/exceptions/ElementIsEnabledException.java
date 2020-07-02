package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementIsEnabledException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementIsEnabledException(String message) {
        super(message);
    }

    public ElementIsEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

}

