package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileExists extends Reason {

    static FileExistsException exception(@NotNull String message) {
        return new FileExistsException(message);
    }

    static FileExistsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsException(message, cause);
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

