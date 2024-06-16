package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FixtureExecutionFailed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FixtureExecutionFailedException extends PerfeccionistaRuntimeException implements FixtureExecutionFailed {

    public FixtureExecutionFailedException(String message) {
        super(message);
    }

    public FixtureExecutionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
