package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementComponentNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementComponentNotDeclaredException(String message) {
        super(message);
    }

    public ElementComponentNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
