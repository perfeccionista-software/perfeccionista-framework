package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class TimeoutsImplementationNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public TimeoutsImplementationNotFoundException(String message) {
        super(message);
    }

    public TimeoutsImplementationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
