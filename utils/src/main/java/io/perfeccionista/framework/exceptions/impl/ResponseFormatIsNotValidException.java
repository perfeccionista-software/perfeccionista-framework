package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ResponseFormatIsNotValid;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ResponseFormatIsNotValidException extends PerfeccionistaRuntimeException implements ResponseFormatIsNotValid {

    public ResponseFormatIsNotValidException(String message) {
        super(message);
    }

    public ResponseFormatIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}
