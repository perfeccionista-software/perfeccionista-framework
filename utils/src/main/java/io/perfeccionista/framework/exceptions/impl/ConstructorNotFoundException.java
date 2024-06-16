package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ConstructorNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ConstructorNotFoundException extends PerfeccionistaRuntimeException implements ConstructorNotFound {

    public ConstructorNotFoundException(String message) {
        super(message);
    }

    public ConstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
