package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverInstantiationException extends PerfeccionistaException {

    public SeleniumWebDriverInstantiationException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverInstantiationException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
