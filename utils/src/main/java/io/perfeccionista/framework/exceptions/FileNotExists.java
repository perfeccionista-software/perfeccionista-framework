package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileNotExists extends Reason {

    static FileNotExistsException exception(@NotNull String message) {
        return new FileNotExistsException(message);
    }

    static FileNotExistsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileNotExistsException(message, cause);
    }

    class FileNotExistsException extends PerfeccionistaRuntimeException implements FileNotExists {

        FileNotExistsException(String message) {
            super(message);
        }

        FileNotExistsException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
