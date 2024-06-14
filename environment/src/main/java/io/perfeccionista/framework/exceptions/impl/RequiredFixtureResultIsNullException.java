package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.RequiredFixtureResultIsNull;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class RequiredFixtureResultIsNullException extends PerfeccionistaRuntimeException implements RequiredFixtureResultIsNull {

    public RequiredFixtureResultIsNullException(String message) {
        super(message);
    }

    public RequiredFixtureResultIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

}
