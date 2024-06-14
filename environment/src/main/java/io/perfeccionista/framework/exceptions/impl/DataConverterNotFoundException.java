package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.DataConverterNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class DataConverterNotFoundException extends PerfeccionistaRuntimeException implements DataConverterNotFound {

    public DataConverterNotFoundException(String message) {
        super(message);
    }

    public DataConverterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
