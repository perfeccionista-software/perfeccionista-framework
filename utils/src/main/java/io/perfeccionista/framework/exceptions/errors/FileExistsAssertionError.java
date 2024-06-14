package io.perfeccionista.framework.exceptions.errors;

import io.perfeccionista.framework.exceptions.FileExists;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;

public class FileExistsAssertionError extends PerfeccionistaAssertionError implements FileExists {

    public FileExistsAssertionError(String message) {
        super(message);
    }

    public FileExistsAssertionError(String message, Throwable cause) {
        super(message, cause);
    }

}
