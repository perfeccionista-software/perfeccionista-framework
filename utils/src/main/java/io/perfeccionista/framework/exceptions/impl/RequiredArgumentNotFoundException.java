package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.RequiredArgumentNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class RequiredArgumentNotFoundException extends PerfeccionistaRuntimeException implements RequiredArgumentNotFound {

    public RequiredArgumentNotFoundException(String message) {
        super(message);
    }

    public RequiredArgumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
