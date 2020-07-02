package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotInFocusException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotInFocusException(String message) {
        super(message);
    }

    public ElementNotInFocusException(String message, Throwable cause) {
        super(message, cause);
    }

}

