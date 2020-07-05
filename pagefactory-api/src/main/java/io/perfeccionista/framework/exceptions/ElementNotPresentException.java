package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ElementNotPresentException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ElementNotPresentException(String message) {
        super(message);
        setProcessed(true);
    }

    public ElementNotPresentException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
