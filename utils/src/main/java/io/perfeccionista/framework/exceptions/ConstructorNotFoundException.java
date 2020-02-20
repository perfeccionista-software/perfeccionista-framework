package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO JavaDoc
 */
public class ConstructorNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ConstructorNotFoundException(String message) {
        super(message);
    }

    public ConstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
