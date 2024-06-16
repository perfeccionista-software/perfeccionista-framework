package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.TestClassNotFound;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class TestClassNotFoundException extends PerfeccionistaRuntimeException implements TestClassNotFound {

    public TestClassNotFoundException(String message) {
        super(message);
    }

    public TestClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
