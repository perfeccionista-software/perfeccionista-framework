package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class RegisterDuplicateException extends PerfeccionistaRuntimeException implements RegisterDuplicate {

    public RegisterDuplicateException(String message) {
        super(message);
    }

    public RegisterDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

}
