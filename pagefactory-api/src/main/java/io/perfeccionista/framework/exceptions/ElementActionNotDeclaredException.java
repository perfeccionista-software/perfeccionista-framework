package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementActionNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementActionNotDeclaredException(String message) {
        super(message);
    }

    public ElementActionNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}

