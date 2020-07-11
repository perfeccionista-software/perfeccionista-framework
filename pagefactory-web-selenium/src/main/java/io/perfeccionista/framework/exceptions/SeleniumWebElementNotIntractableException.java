package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebElementNotIntractableException extends PerfeccionistaException {

    public SeleniumWebElementNotIntractableException(@NotNull String message) {
        super(message);
        setProcessed(true);
    }

    public SeleniumWebElementNotIntractableException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
