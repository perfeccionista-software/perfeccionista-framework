package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FixtureNotParametrized;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FixtureNotParametrizedException extends PerfeccionistaRuntimeException implements FixtureNotParametrized {

    public FixtureNotParametrizedException(String message) {
        super(message);
    }

    public FixtureNotParametrizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
