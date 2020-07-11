package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverException extends PerfeccionistaException {

    public SeleniumWebDriverException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
