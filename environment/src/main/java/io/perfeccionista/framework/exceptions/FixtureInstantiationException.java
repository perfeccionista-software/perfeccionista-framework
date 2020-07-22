package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class FixtureInstantiationException extends PerfeccionistaException {
    private static final long serialVersionUID = 1L;

    public FixtureInstantiationException(String message) {
        super(message);
    }

    public FixtureInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
