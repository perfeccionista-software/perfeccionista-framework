package io.perfeccionista.framework.exceptions.errors;

import io.perfeccionista.framework.exceptions.NumberValueNotMatch;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;

public class NumberValueNotMatchAssertionError extends PerfeccionistaAssertionError implements NumberValueNotMatch {

    public NumberValueNotMatchAssertionError(String message) {
        super(message);
    }

    public NumberValueNotMatchAssertionError(String message, Throwable cause) {
        super(message, cause);
    }

}
