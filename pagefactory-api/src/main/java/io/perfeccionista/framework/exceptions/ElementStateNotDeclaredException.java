package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementStateNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementStateNotDeclaredException(String message) {
        super(message);
    }

    public ElementStateNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
