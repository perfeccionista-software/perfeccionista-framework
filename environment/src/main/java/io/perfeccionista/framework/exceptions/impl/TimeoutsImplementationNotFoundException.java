package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.TimeoutsImplementationNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class TimeoutsImplementationNotFoundException extends PerfeccionistaRuntimeException implements TimeoutsImplementationNotFound {

    public TimeoutsImplementationNotFoundException(String message) {
        super(message);
    }

    public TimeoutsImplementationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
