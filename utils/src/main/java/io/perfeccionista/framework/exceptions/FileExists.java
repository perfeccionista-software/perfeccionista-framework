package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileExists extends Reason {

    static FileExistsAssertionError assertionError(@NotNull String message) {
        return new FileExistsAssertionError(message);
    }

    static FileExistsAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsAssertionError(message, cause);
    }

    static FileExistsException exception(@NotNull String message) {
        return new FileExistsException(message);
    }

    static FileExistsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsException(message, cause);
    }

    class FileExistsAssertionError extends PerfeccionistaAssertionError implements FileExists {

        FileExistsAssertionError(String message) {
            super(message);
        }

        FileExistsAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class FileExistsException extends PerfeccionistaRuntimeException implements FileExists {

        FileExistsException(String message) {
            super(message);
        }

        FileExistsException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
