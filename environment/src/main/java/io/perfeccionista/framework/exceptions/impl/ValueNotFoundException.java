package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ValueNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ValueNotFoundException extends PerfeccionistaRuntimeException implements ValueNotFound {

    public ValueNotFoundException(String message) {
        super(message);
    }

    public ValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
