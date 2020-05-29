package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebDriverInstanceNotStartedException extends PerfeccionistaException {

    public WebDriverInstanceNotStartedException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public WebDriverInstanceNotStartedException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
