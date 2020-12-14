package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebColorFormatNotResolved extends Reason {

    static WebColorFormatNotResolvedException exception(@NotNull String message) {
        return new WebColorFormatNotResolvedException(message);
    }

    static WebColorFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebColorFormatNotResolvedException(message, cause);
    }

    class WebColorFormatNotResolvedException extends PerfeccionistaRuntimeException implements WebColorFormatNotResolved {

        WebColorFormatNotResolvedException(String message) {
            super(message);
        }

        WebColorFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
