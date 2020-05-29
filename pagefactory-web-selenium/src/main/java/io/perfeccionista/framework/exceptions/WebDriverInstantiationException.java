package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebDriverInstantiationException extends PerfeccionistaException {

    public WebDriverInstantiationException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public WebDriverInstantiationException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
