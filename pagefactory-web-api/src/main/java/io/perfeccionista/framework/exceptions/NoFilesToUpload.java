package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface NoFilesToUpload extends Reason {

    static NoFilesToUploadException exception(@NotNull String message) {
        return new NoFilesToUploadException(message);
    }

    static NoFilesToUploadException exception(@NotNull String message, @NotNull Throwable cause) {
        return new NoFilesToUploadException(message, cause);
    }

    class NoFilesToUploadException extends PerfeccionistaRuntimeException implements NoFilesToUpload {

        NoFilesToUploadException(String message) {
            super(message);
        }

        NoFilesToUploadException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
