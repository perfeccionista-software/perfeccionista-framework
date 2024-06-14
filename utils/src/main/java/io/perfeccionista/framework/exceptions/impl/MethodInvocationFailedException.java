package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.MethodNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class MethodInvocationFailedException extends PerfeccionistaRuntimeException implements MethodNotFound {

    public MethodInvocationFailedException(String message) {
        super(message);
    }

    public MethodInvocationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
