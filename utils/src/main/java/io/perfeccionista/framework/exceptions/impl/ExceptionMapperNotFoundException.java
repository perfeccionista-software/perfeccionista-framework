package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ExceptionMapperNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ExceptionMapperNotFoundException extends PerfeccionistaRuntimeException implements ExceptionMapperNotFound {

    public ExceptionMapperNotFoundException(String message) {
        super(message);
    }

    public ExceptionMapperNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
