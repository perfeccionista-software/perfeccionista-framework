package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class WebDriverBinaryNotDeclaredException extends PerfeccionistaException {

    public WebDriverBinaryNotDeclaredException(@NotNull String message) {
        super(message);
        setService(true);
    }

    public WebDriverBinaryNotDeclaredException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setService(true);
    }

}
