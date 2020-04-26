package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public class ExtractingClipboardValueException extends PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    public ExtractingClipboardValueException(String message) {
        super(message);
    }

    public ExtractingClipboardValueException(String message, Throwable cause) {
        super(message, cause);
    }

}

