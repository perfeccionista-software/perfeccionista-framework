package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class TableColumnLocatorNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public TableColumnLocatorNotDeclaredException(String message) {
        super(message);
    }

    public TableColumnLocatorNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}
