package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface LocatorNotFound extends Reason {

    static LocatorNotFoundException exception(@NotNull String message) {
        return new LocatorNotFoundException(message);
    }

    static LocatorNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new LocatorNotFoundException(message, cause);
    }

    class LocatorNotFoundException extends PerfeccionistaRuntimeException implements LocatorNotFound {

        LocatorNotFoundException(String message) {
            super(message);
        }

        LocatorNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
