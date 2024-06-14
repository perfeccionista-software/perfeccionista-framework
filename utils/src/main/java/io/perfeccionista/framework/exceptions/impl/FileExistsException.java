package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FileExistsException extends PerfeccionistaRuntimeException implements FileExists {

    public FileExistsException(String message) {
        super(message);
    }

    public FileExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
