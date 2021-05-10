package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface EndpointHandlerNotFound extends Reason {

    static EndpointHandlerNotFoundException exception(@NotNull String message) {
        return new EndpointHandlerNotFoundException(message);
    }

    static EndpointHandlerNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EndpointHandlerNotFoundException(message, cause);
    }

    class EndpointHandlerNotFoundException extends PerfeccionistaRuntimeException implements EndpointHandlerNotFound {

        EndpointHandlerNotFoundException(String message) {
            super(message);
        }

        EndpointHandlerNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
