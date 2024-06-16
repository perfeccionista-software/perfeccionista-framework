package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.IncorrectInvocationRunnerLogic;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class IncorrectInvocationRunnerLogicException extends PerfeccionistaRuntimeException implements IncorrectInvocationRunnerLogic {

    public IncorrectInvocationRunnerLogicException(String message) {
        super(message);
    }

    public IncorrectInvocationRunnerLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
