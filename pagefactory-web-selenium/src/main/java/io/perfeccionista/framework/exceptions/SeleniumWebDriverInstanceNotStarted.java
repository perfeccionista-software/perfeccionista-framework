package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverInstanceNotStarted extends Reason {

    static SeleniumWebDriverInstanceNotStartedException exception(@NotNull String message) {
        return new SeleniumWebDriverInstanceNotStartedException(message);
    }

    static SeleniumWebDriverInstanceNotStartedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverInstanceNotStartedException(message, cause);
    }

    class SeleniumWebDriverInstanceNotStartedException extends PerfeccionistaRuntimeException implements SeleniumWebDriverInstanceNotStarted {

        SeleniumWebDriverInstanceNotStartedException(String message) {
            super(message);
        }

        SeleniumWebDriverInstanceNotStartedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
