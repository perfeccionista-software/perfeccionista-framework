package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverInstanceInternalException extends PerfeccionistaException {

    public SeleniumWebDriverInstanceInternalException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverInstanceInternalException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
