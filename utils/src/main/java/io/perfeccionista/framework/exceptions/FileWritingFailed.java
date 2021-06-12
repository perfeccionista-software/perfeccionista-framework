package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FileWritingFailed extends Reason {

    static FileWritingFailedException exception(@NotNull String message) {
        return new FileWritingFailedException(message);
    }

    static FileWritingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileWritingFailedException(message, cause);
    }

    class FileWritingFailedException extends PerfeccionistaRuntimeException implements FileWritingFailed {

        FileWritingFailedException(String message) {
            super(message);
        }

        FileWritingFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
