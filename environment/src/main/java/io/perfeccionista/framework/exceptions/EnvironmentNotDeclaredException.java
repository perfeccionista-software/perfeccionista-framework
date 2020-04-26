package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class EnvironmentNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public EnvironmentNotDeclaredException(String message) {
        super(message);
    }

    public EnvironmentNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
