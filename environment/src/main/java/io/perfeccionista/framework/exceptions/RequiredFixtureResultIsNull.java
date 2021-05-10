package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface RequiredFixtureResultIsNull extends Reason {

    static RequiredFixtureResultIsNullException exception(@NotNull String message) {
        return new RequiredFixtureResultIsNullException(message);
    }

    static RequiredFixtureResultIsNullException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RequiredFixtureResultIsNullException(message, cause);
    }

    class RequiredFixtureResultIsNullException extends PerfeccionistaRuntimeException implements RequiredFixtureResultIsNull {

        RequiredFixtureResultIsNullException(String message) {
            super(message);
        }

        RequiredFixtureResultIsNullException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
