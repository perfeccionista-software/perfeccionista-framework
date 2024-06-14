package io.perfeccionista.framework.exceptions.errors;

import io.perfeccionista.framework.exceptions.StringValueNotMatch;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;

public class StringValueNotMatchAssertionError extends PerfeccionistaAssertionError implements StringValueNotMatch {

    public StringValueNotMatchAssertionError(String message) {
        super(message);
    }

    public StringValueNotMatchAssertionError(String message, Throwable cause) {
        super(message, cause);
    }

}
