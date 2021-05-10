package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ElementNotIntractable extends Reason {

    static ElementNotIntractableException exception(@NotNull String message) {
        return new ElementNotIntractableException(message);
    }

    static ElementNotIntractableException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ElementNotIntractableException(message, cause);
    }

    class ElementNotIntractableException extends PerfeccionistaRuntimeException implements ElementNotIntractable {

        ElementNotIntractableException(String message) {
            super(message);
        }

        ElementNotIntractableException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
