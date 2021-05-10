package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementPathNotResolved extends Reason {

    static ElementPathNotResolvedException exception(@NotNull String message) {
        return new ElementPathNotResolvedException(message);
    }

    static ElementPathNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementPathNotResolvedException(message, cause);
    }

    class ElementPathNotResolvedException extends PerfeccionistaRuntimeException implements ElementPathNotResolved {

        ElementPathNotResolvedException(String message) {
            super(message);
        }

        ElementPathNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
