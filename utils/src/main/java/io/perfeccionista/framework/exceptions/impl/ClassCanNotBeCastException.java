package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ClassCanNotBeCast;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ClassCanNotBeCastException extends PerfeccionistaRuntimeException implements ClassCanNotBeCast {

    public ClassCanNotBeCastException(String message) {
        super(message);
    }

    public ClassCanNotBeCastException(String message, Throwable cause) {
        super(message, cause);
    }

}
