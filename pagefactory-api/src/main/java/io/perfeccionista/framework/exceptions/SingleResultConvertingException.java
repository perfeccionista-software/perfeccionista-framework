package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class SingleResultConvertingException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public SingleResultConvertingException(String message) {
        super(message);
    }

    public SingleResultConvertingException(String message, Throwable cause) {
        super(message, cause);
    }

}

