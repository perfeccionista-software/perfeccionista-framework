package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class IncorrectServiceConfigurationException extends PerfeccionistaRuntimeException implements IncorrectServiceConfiguration {

    public IncorrectServiceConfigurationException(String message) {
        super(message);
    }

    public IncorrectServiceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
