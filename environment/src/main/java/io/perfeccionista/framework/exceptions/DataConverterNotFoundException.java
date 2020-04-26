package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class DataConverterNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public DataConverterNotFoundException(String message) {
        super(message);
    }

    public DataConverterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
