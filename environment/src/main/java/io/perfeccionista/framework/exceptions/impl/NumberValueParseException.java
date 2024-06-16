package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.NumberValueParse;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class NumberValueParseException extends PerfeccionistaRuntimeException implements NumberValueParse {

    public NumberValueParseException(String message) {
        super(message);
    }

    public NumberValueParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
