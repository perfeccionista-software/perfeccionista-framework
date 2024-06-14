package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FieldNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FieldNotFoundException extends PerfeccionistaRuntimeException implements FieldNotFound {

    public FieldNotFoundException(String message) {
        super(message);
    }

    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
