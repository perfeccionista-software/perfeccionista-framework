package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotSelectedException  extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotSelectedException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementNotSelectedException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}

