package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebLocatorProcessingException extends PerfeccionistaException {

    public WebLocatorProcessingException(@NotNull String message) {
        super(message);
    }

    public WebLocatorProcessingException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
