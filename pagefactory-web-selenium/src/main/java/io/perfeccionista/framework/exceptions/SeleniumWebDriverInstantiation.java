package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverInstantiation extends Reason {

    static SeleniumWebDriverInstantiationException exception(@NotNull String message) {
        return new SeleniumWebDriverInstantiationException(message);
    }

    static SeleniumWebDriverInstantiationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverInstantiationException(message, cause);
    }

    class SeleniumWebDriverInstantiationException extends PerfeccionistaRuntimeException implements SeleniumWebDriverInstantiation {

        SeleniumWebDriverInstantiationException(String message) {
            super(message);
        }

        SeleniumWebDriverInstantiationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

