package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.EmptyPath;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class EmptyPathException extends PerfeccionistaRuntimeException implements EmptyPath {

    public EmptyPathException(String message) {
        super(message);
    }

    public EmptyPathException(String message, Throwable cause) {
        super(message, cause);
    }

}
