package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileReadingFailed extends Reason {

    static FileReadingFailedException exception(@NotNull String message) {
        return new FileReadingFailedException(message);
    }

    static FileReadingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileReadingFailedException(message, cause);
    }

    class FileReadingFailedException extends PerfeccionistaRuntimeException implements FileReadingFailed {

        FileReadingFailedException(String message) {
            super(message);
        }

        FileReadingFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
