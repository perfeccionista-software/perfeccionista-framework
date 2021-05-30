package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface IncorrectMobileLocatorValue extends Reason {

    static IncorrectMobileLocatorValueException exception(@NotNull String message) {
        return new IncorrectMobileLocatorValueException(message);
    }

    static IncorrectMobileLocatorValueException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectMobileLocatorValueException(message, cause);
    }

    class IncorrectMobileLocatorValueException extends PerfeccionistaRuntimeException implements IncorrectMobileLocatorValue {

        IncorrectMobileLocatorValueException(String message) {
            super(message);
        }

        IncorrectMobileLocatorValueException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
