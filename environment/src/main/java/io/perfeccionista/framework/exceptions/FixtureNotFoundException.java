package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class FixtureNotFoundException extends PerfeccionistaException {
    private static final long serialVersionUID = 1L;

    public FixtureNotFoundException(String message) {
        super(message);
    }

    public FixtureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
