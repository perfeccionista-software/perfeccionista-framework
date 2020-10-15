package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebMappedBlockNotFound extends Reason {

    static WebMappedBlockNotFoundException exception(@NotNull String message) {
        return new WebMappedBlockNotFoundException(message);
    }

    static WebMappedBlockNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebMappedBlockNotFoundException(message, cause);
    }

    class WebMappedBlockNotFoundException extends PerfeccionistaRuntimeException implements WebMappedBlockNotFound {

        WebMappedBlockNotFoundException(String message) {
            super(message);
        }

        WebMappedBlockNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
