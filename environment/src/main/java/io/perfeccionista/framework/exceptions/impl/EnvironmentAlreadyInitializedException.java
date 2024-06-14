package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.EnvironmentAlreadyInitialized;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class EnvironmentAlreadyInitializedException extends PerfeccionistaRuntimeException implements EnvironmentAlreadyInitialized {

    public EnvironmentAlreadyInitializedException(String message) {
        super(message);
    }

    public EnvironmentAlreadyInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

}
