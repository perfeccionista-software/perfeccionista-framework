package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ServiceNotFound extends Reason {

    static ServiceNotFoundException exception(@NotNull String message) {
        return new ServiceNotFoundException(message);
    }

    static ServiceNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ServiceNotFoundException(message, cause);
    }

    class ServiceNotFoundException extends PerfeccionistaRuntimeException implements ServiceNotFound {

        ServiceNotFoundException(String message) {
            super(message);
        }

        ServiceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

