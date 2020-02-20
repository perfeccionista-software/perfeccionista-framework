package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

/**
 * TODO: JavaDoc
 */
public class RegisterDuplicateException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public RegisterDuplicateException(String message) {
        super(message);
    }

    public RegisterDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

}