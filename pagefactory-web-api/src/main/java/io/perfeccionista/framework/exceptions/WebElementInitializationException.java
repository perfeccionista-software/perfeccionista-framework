package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebElementInitializationException extends PerfeccionistaException {

    public WebElementInitializationException(@NotNull String message) {
        super(message);
    }

    public WebElementInitializationException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
