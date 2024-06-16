package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.DataSourceNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class DataSourceNotFoundException extends PerfeccionistaRuntimeException implements DataSourceNotFound {

    public DataSourceNotFoundException(String message) {
        super(message);
    }

    public DataSourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
