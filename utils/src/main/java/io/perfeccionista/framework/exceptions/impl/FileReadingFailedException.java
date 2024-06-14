package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FileReadingFailed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FileReadingFailedException extends PerfeccionistaRuntimeException implements FileReadingFailed {

    public FileReadingFailedException(String message) {
        super(message);
    }

    public FileReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
