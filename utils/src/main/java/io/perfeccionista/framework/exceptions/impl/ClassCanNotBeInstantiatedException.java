package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ClassCanNotBeInstantiated;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ClassCanNotBeInstantiatedException extends PerfeccionistaRuntimeException implements ClassCanNotBeInstantiated {

    public ClassCanNotBeInstantiatedException(String message) {
        super(message);
    }

    public ClassCanNotBeInstantiatedException(String message, Throwable cause) {
        super(message, cause);
    }

}
