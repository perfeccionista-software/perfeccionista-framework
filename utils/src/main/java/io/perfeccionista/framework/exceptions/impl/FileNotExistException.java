package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.FileNotExist;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class FileNotExistException extends PerfeccionistaRuntimeException implements FileNotExist {

    public FileNotExistException(String message) {
        super(message);
    }

    public FileNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
