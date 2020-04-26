package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebElementExistsException extends PerfeccionistaException {

    public WebElementExistsException(@NotNull String message) {
        super(message);
        setProcessed(true);
    }

    public WebElementExistsException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
