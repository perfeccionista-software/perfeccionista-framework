package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.EnvironmentNotConfigured;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class EnvironmentNotConfiguredException extends PerfeccionistaRuntimeException implements EnvironmentNotConfigured {

    public EnvironmentNotConfiguredException(String message) {
        super(message);
    }

    public EnvironmentNotConfiguredException(String message, Throwable cause) {
        super(message, cause);
    }

}
