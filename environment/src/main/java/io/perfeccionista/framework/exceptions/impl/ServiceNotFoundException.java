package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ServiceNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ServiceNotFoundException extends PerfeccionistaRuntimeException implements ServiceNotFound {

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
