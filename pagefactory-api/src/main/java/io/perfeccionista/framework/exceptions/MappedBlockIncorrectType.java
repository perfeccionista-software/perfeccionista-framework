package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MappedBlockIncorrectType extends Reason {

    static MappedBlockIncorrectTypeException exception(@NotNull String message) {
        return new MappedBlockIncorrectTypeException(message);
    }

    static MappedBlockIncorrectTypeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MappedBlockIncorrectTypeException(message, cause);
    }

    class MappedBlockIncorrectTypeException extends PerfeccionistaRuntimeException implements MappedBlockIncorrectType {

        MappedBlockIncorrectTypeException(String message) {
            super(message);
        }

        MappedBlockIncorrectTypeException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
