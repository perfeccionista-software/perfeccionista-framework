package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebExtractorNotResolved extends Reason {

    static WebExtractorNotResolvedException exception(@NotNull String message) {
        return new WebExtractorNotResolvedException(message);
    }

    static WebExtractorNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebExtractorNotResolvedException(message, cause);
    }

    class WebExtractorNotResolvedException extends PerfeccionistaRuntimeException implements WebExtractorNotResolved {

        WebExtractorNotResolvedException(String message) {
            super(message);
        }

        WebExtractorNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
