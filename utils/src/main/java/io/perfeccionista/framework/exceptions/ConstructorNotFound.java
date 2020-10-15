package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO JavaDoc
 */
public interface ConstructorNotFound extends Reason {

    static ConstructorNotFoundException exception(@NotNull String message) {
        return new ConstructorNotFoundException(message);
    }

    static ConstructorNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ConstructorNotFoundException(message, cause);
    }

    class ConstructorNotFoundException extends PerfeccionistaRuntimeException implements ConstructorNotFound {

        ConstructorNotFoundException(String message) {
            super(message);
        }

        ConstructorNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
