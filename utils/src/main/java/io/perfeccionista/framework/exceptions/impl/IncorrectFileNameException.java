package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.IncorrectFileName;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class IncorrectFileNameException extends PerfeccionistaRuntimeException implements IncorrectFileName {

    public IncorrectFileNameException(String message) {
        super(message);
    }

    public IncorrectFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

}
