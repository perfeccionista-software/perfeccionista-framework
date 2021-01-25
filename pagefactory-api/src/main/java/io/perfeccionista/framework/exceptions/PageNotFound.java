package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface PageNotFound extends Reason {

    static PageNotFoundException exception(@NotNull String message) {
        return new PageNotFoundException(message);
    }

    static PageNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new PageNotFoundException(message, cause);
    }

    class PageNotFoundException extends PerfeccionistaRuntimeException implements PageNotFound {

        PageNotFoundException(String message) {
            super(message);
        }

        PageNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
