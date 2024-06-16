package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ServiceNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface ServiceNotFound extends Reason {

    static ServiceNotFoundException exception(@NotNull String message) {
        return new ServiceNotFoundException(message);
    }

    static ServiceNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ServiceNotFoundException(message, cause);
    }

}

