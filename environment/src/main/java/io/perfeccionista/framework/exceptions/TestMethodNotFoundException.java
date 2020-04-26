package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class TestMethodNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public TestMethodNotFoundException(String message) {
        super(message);
    }

    public TestMethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
