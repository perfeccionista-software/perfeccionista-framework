package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementIsDisabledException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementIsDisabledException(String message) {
        super(message);
    }

    public ElementIsDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

}
