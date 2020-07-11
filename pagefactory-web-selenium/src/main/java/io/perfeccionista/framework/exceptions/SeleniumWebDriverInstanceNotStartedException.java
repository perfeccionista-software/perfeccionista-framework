package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverInstanceNotStartedException extends PerfeccionistaException {

    public SeleniumWebDriverInstanceNotStartedException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverInstanceNotStartedException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
