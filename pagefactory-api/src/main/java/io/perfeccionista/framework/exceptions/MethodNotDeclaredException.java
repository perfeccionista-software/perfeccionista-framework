package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class MethodNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public MethodNotDeclaredException(String message) {
        super(message);
    }

    public MethodNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
