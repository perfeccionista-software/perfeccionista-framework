package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MappedWebBlockIdentifierCall extends Reason {

    static MappedWebBlockIdentifierCallException exception(@NotNull String message) {
        return new MappedWebBlockIdentifierCallException(message);
    }

    static MappedWebBlockIdentifierCallException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MappedWebBlockIdentifierCallException(message, cause);
    }

    class MappedWebBlockIdentifierCallException extends PerfeccionistaRuntimeException implements MappedWebBlockIdentifierCall {

        MappedWebBlockIdentifierCallException(String message) {
            super(message);
        }

        MappedWebBlockIdentifierCallException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
