package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.EnvironmentNotInitialized;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class EnvironmentNotInitializedException extends PerfeccionistaRuntimeException implements EnvironmentNotInitialized {

    public EnvironmentNotInitializedException(String message) {
        super(message);
    }

    public EnvironmentNotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

}
