package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverInstanceNotAvailableException extends PerfeccionistaException {

    public SeleniumWebDriverInstanceNotAvailableException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverInstanceNotAvailableException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
