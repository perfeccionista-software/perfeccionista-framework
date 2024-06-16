package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ClassNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ClassNotFoundException extends PerfeccionistaRuntimeException implements ClassNotFound {

    public ClassNotFoundException(String message) {
        super(message);
    }

    public ClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
