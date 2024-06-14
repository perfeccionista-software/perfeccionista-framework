package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.MethodNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class MethodNotFoundException extends PerfeccionistaRuntimeException implements MethodNotFound {

    public MethodNotFoundException(String message) {
        super(message);
    }

    public MethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
