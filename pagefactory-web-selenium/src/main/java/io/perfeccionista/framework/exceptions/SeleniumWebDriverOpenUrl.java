package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverOpenUrl extends Reason {

    static SeleniumWebDriverOpenUrlException exception(@NotNull String message) {
        return new SeleniumWebDriverOpenUrlException(message);
    }

    static SeleniumWebDriverOpenUrlException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverOpenUrlException(message, cause);
    }

    class SeleniumWebDriverOpenUrlException extends PerfeccionistaRuntimeException implements SeleniumWebDriverOpenUrl {

        SeleniumWebDriverOpenUrlException(String message) {
            super(message);
        }

        SeleniumWebDriverOpenUrlException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
