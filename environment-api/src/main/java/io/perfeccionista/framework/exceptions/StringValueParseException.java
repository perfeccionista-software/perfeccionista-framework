package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class StringValueParseException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public StringValueParseException(String message) {
        super(message);
    }

    public StringValueParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
