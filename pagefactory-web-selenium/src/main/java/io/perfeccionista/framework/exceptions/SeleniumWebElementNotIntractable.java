package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebElementNotIntractable extends Reason {

    static SeleniumWebElementNotIntractableException exception(@NotNull String message) {
        return new SeleniumWebElementNotIntractableException(message);
    }

    static SeleniumWebElementNotIntractableException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebElementNotIntractableException(message, cause);
    }

    class SeleniumWebElementNotIntractableException extends PerfeccionistaRuntimeException implements SeleniumWebElementNotIntractable {

        SeleniumWebElementNotIntractableException(String message) {
            super(message);
        }

        SeleniumWebElementNotIntractableException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
