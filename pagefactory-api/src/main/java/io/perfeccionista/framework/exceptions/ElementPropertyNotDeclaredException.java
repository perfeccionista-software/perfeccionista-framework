package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementPropertyNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementPropertyNotDeclaredException(String message) {
        super(message);
    }

    public ElementPropertyNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
