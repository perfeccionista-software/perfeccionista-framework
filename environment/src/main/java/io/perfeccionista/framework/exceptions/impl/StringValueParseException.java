package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.StringValueParse;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class StringValueParseException extends PerfeccionistaRuntimeException implements StringValueParse {

    public StringValueParseException(String message) {
        super(message);
    }

    public StringValueParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
