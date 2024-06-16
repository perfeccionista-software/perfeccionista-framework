package io.perfeccionista.framework.exceptions.errors;

import io.perfeccionista.framework.exceptions.FileNotExist;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;

public class FileNotExistAssertionError extends PerfeccionistaAssertionError implements FileNotExist {

    public FileNotExistAssertionError(String message) {
        super(message);
    }

    public FileNotExistAssertionError(String message, Throwable cause) {
        super(message, cause);
    }

}
