package io.perfeccionista.framework.exceptions.errors;

import io.perfeccionista.framework.exceptions.ObjectValueNotMatch;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;

public class ObjectValueNotMatchAssertionError extends PerfeccionistaAssertionError implements ObjectValueNotMatch {

    public ObjectValueNotMatchAssertionError(String message) {
        super(message);
    }

    public ObjectValueNotMatchAssertionError(String message, Throwable cause) {
        super(message, cause);
    }

}
