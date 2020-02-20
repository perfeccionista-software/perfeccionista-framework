package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class TestClassNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public TestClassNotFoundException(String message) {
        super(message);
    }

    public TestClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
