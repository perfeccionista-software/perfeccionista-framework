package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class NodeHashNotCalculatedException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public NodeHashNotCalculatedException(String message) {
        super(message);
    }

    public NodeHashNotCalculatedException(String message, Throwable cause) {
        super(message, cause);
    }


}
