package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class ActionRunnerImplementationNotFoundException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ActionRunnerImplementationNotFoundException(String message) {
        super(message);
    }

    public ActionRunnerImplementationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
