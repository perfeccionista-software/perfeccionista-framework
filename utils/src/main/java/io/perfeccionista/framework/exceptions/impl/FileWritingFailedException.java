package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FileWritingFailed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FileWritingFailedException extends PerfeccionistaRuntimeException implements FileWritingFailed {

    public FileWritingFailedException(String message) {
        super(message);
    }

    public FileWritingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
