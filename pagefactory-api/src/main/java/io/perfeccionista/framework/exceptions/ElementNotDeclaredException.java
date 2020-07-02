package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotDeclaredException(String message) {
        super(message);
    }

    public ElementNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}

