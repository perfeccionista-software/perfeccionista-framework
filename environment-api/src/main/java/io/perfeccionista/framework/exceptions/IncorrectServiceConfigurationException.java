package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class IncorrectServiceConfigurationException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public IncorrectServiceConfigurationException(String message) {
        super(message);
    }

    public IncorrectServiceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}