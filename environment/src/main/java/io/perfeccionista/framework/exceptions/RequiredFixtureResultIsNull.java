package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.RequiredFixtureResultIsNullException;
import org.jetbrains.annotations.NotNull;

public interface RequiredFixtureResultIsNull extends Reason {

    static RequiredFixtureResultIsNullException exception(@NotNull String message) {
        return new RequiredFixtureResultIsNullException(message);
    }

    static RequiredFixtureResultIsNullException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RequiredFixtureResultIsNullException(message, cause);
    }

}
