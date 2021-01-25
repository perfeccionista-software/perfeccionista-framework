package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementStaleReference extends Reason {

    static ElementStaleReference.ElementStaleReferenceException exception(@NotNull String message) {
        return new ElementStaleReferenceException(message);
    }

    static ElementStaleReference.ElementStaleReferenceException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementStaleReferenceException(message, cause);
    }

    class ElementStaleReferenceException extends PerfeccionistaRuntimeException implements ElementStaleReference {

        ElementStaleReferenceException(String message) {
            super(message);
        }

        ElementStaleReferenceException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
