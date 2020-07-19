package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class TableColumnNotDeclaredException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public TableColumnNotDeclaredException(String message) {
        super(message);
    }

    public TableColumnNotDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

}

