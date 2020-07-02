package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementLabelValueException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementLabelValueException(String message) {
        super(message);
    }

    public ElementLabelValueException(String message, Throwable cause) {
        super(message, cause);
    }

}

