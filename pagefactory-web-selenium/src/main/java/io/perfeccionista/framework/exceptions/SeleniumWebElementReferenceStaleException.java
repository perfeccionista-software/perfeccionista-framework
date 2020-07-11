package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebElementReferenceStaleException extends PerfeccionistaException {

    public SeleniumWebElementReferenceStaleException(@NotNull String message) {
        super(message);
        setProcessed(true);
    }

    public SeleniumWebElementReferenceStaleException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}

