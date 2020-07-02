package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementTextValueException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementTextValueException(String message) {
        super(message);
    }

    public ElementTextValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
