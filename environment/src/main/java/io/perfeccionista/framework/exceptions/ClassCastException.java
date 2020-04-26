package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class ClassCastException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ClassCastException(String message) {
        super(message);
    }

    public ClassCastException(String message, Throwable cause) {
        super(message, cause);
    }

}
