package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class PreconditionViolationException extends PerfeccionistaRuntimeException implements PreconditionViolation {

    public PreconditionViolationException(String message) {
        super(message);
    }

    public PreconditionViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
