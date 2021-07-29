package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebElementIncorrectType extends Reason {

    static SeleniumWebElementIncorrectTypeException exception(@NotNull String message) {
        return new SeleniumWebElementIncorrectTypeException(message);
    }

    static SeleniumWebElementIncorrectTypeException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebElementIncorrectTypeException(message, cause);
    }

    class SeleniumWebElementIncorrectTypeException extends PerfeccionistaRuntimeException implements SeleniumWebElementIncorrectType {

        SeleniumWebElementIncorrectTypeException(String message) {
            super(message);
        }

        SeleniumWebElementIncorrectTypeException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
