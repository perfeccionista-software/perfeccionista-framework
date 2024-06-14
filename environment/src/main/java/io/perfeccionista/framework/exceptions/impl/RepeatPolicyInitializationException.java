package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.RepeatPolicyInitialization;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class RepeatPolicyInitializationException extends PerfeccionistaRuntimeException implements RepeatPolicyInitialization {

    public RepeatPolicyInitializationException(String message) {
        super(message);
    }

    public RepeatPolicyInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

}
