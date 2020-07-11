package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class FileNotExistsException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public FileNotExistsException(String message) {
        super(message);
        setProcessed(true);
    }

    public FileNotExistsException(String message, Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
