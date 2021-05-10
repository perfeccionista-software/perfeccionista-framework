package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementMethodNotImplemented extends Reason {

    static ElementMethodNotImplementedException exception(@NotNull String message) {
        return new ElementMethodNotImplementedException(message);
    }

    static ElementMethodNotImplementedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementMethodNotImplementedException(message, cause);
    }

    class ElementMethodNotImplementedException extends PerfeccionistaRuntimeException implements ElementMethodNotImplemented {

        ElementMethodNotImplementedException(String message) {
            super(message);
        }

        ElementMethodNotImplementedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

