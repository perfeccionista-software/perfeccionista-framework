package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileNotExist extends Reason {

    static FileNotExistAssertionError assertionError(@NotNull String message) {
        return new FileNotExistAssertionError(message);
    }

    static FileNotExistAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new FileNotExistAssertionError(message, cause);
    }

    static FileNotExistException exception(@NotNull String message) {
        return new FileNotExistException(message);
    }

    static FileNotExistException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileNotExistException(message, cause);
    }

    class FileNotExistAssertionError extends PerfeccionistaAssertionError implements FileNotExist {

        FileNotExistAssertionError(String message) {
            super(message);
        }

        FileNotExistAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class FileNotExistException extends PerfeccionistaRuntimeException implements FileNotExist {

        FileNotExistException(String message) {
            super(message);
        }

        FileNotExistException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
