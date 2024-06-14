package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FieldAccess;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FieldAccessException extends PerfeccionistaRuntimeException implements FieldAccess {

    public FieldAccessException(String message) {
        super(message);
    }

    public FieldAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
