package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverInstanceNotAvailable extends Reason {

    static SeleniumWebDriverInstanceNotAvailableException exception(@NotNull String message) {
        return new SeleniumWebDriverInstanceNotAvailableException(message);
    }

    static SeleniumWebDriverInstanceNotAvailableException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverInstanceNotAvailableException(message, cause);
    }

    class SeleniumWebDriverInstanceNotAvailableException extends PerfeccionistaRuntimeException implements SeleniumWebDriverInstanceNotAvailable {

        SeleniumWebDriverInstanceNotAvailableException(String message) {
            super(message);
        }

        SeleniumWebDriverInstanceNotAvailableException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
