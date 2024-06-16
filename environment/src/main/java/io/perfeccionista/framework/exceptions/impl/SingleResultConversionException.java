package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.SingleResultConversion;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class SingleResultConversionException extends PerfeccionistaRuntimeException implements SingleResultConversion {

    public SingleResultConversionException(String message) {
        super(message);
    }

    public SingleResultConversionException(String message, Throwable cause) {
        super(message, cause);
    }

}
