package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class RepeatPolicyInitializationException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public RepeatPolicyInitializationException(String message) {
        super(message);
    }

    public RepeatPolicyInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

}