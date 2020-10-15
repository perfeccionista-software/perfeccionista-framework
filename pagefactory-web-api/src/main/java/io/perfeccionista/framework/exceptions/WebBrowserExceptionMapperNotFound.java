package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserExceptionMapperNotFound extends Reason {

    static WebBrowserExceptionMapperNotFoundException exception(@NotNull String message) {
        return new WebBrowserExceptionMapperNotFoundException(message);
    }

    static WebBrowserExceptionMapperNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new WebBrowserExceptionMapperNotFoundException(message, cause);
    }

    class WebBrowserExceptionMapperNotFoundException extends PerfeccionistaRuntimeException implements WebBrowserExceptionMapperNotFound {

        WebBrowserExceptionMapperNotFoundException(String message) {
            super(message);
        }

        WebBrowserExceptionMapperNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
