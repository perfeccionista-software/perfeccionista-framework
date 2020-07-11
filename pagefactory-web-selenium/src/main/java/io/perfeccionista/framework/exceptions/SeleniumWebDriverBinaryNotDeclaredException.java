package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class SeleniumWebDriverBinaryNotDeclaredException extends PerfeccionistaException {

    public SeleniumWebDriverBinaryNotDeclaredException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public SeleniumWebDriverBinaryNotDeclaredException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
