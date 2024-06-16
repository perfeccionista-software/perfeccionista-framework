package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.TestMethodNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class TestMethodNotFoundException extends PerfeccionistaRuntimeException implements TestMethodNotFound {

    public TestMethodNotFoundException(String message) {
        super(message);
    }

    public TestMethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
