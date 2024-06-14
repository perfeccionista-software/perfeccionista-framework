package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FixtureNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FixtureNotFoundException extends PerfeccionistaRuntimeException implements FixtureNotFound {

    public FixtureNotFoundException(String message) {
        super(message);
    }

    public FixtureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
