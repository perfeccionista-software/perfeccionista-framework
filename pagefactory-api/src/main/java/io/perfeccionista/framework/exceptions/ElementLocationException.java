package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementLocationException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementLocationException(String message) {
        super(message);
    }

    public ElementLocationException(String message, Throwable cause) {
        super(message, cause);
    }

}
