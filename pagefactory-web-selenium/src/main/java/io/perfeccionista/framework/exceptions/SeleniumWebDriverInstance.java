package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverInstance extends Reason {

    static SeleniumWebDriverInstanceException exception(@NotNull String message) {
        return new SeleniumWebDriverInstanceException(message);
    }

    static SeleniumWebDriverInstanceException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverInstanceException(message, cause);
    }

    class SeleniumWebDriverInstanceException extends PerfeccionistaRuntimeException implements SeleniumWebDriverInstance {

        SeleniumWebDriverInstanceException(String message) {
            super(message);
        }

        SeleniumWebDriverInstanceException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
