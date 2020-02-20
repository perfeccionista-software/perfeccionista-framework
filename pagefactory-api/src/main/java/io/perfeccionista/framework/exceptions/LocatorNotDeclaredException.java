package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class LocatorNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public LocatorNotDeclaredException(String message) {
        super(message);
    }

    public LocatorNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
