package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementCastException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementCastException(String message) {
        super(message);
    }

    public ElementCastException(String message, Throwable cause) {
        super(message, cause);
    }

}
