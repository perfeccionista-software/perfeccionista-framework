package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebMappedBlockIncorrectType extends Reason {

    static WebMappedBlockIncorrectTypeException exception(@NotNull String message) {
        return new WebMappedBlockIncorrectTypeException(message);
    }

    static WebMappedBlockIncorrectTypeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebMappedBlockIncorrectTypeException(message, cause);
    }

    class WebMappedBlockIncorrectTypeException extends PerfeccionistaRuntimeException implements WebMappedBlockIncorrectType {

        WebMappedBlockIncorrectTypeException(String message) {
            super(message);
        }

        WebMappedBlockIncorrectTypeException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
