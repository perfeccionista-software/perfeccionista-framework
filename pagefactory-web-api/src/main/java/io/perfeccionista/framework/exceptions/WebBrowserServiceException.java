package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebBrowserServiceException extends PerfeccionistaException {

    public WebBrowserServiceException(@NotNull String message) {
        super(message);
    }

    public WebBrowserServiceException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}

