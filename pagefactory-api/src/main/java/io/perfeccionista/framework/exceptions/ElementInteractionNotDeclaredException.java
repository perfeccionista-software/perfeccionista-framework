package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementInteractionNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementInteractionNotDeclaredException(String message) {
        super(message);
    }

    public ElementInteractionNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
