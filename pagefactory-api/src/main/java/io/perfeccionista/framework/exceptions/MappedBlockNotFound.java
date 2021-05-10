package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MappedBlockNotFound extends Reason {

    static MappedBlockNotFoundException exception(@NotNull String message) {
        return new MappedBlockNotFoundException(message);
    }

    static MappedBlockNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MappedBlockNotFoundException(message, cause);
    }

    class MappedBlockNotFoundException extends PerfeccionistaRuntimeException implements MappedBlockNotFound {

        MappedBlockNotFoundException(String message) {
            super(message);
        }

        MappedBlockNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
