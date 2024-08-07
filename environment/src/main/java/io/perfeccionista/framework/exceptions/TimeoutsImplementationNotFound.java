package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.TimeoutsImplementationNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface TimeoutsImplementationNotFound extends Reason {

    static TimeoutsImplementationNotFoundException exception(@NotNull String message) {
        return new TimeoutsImplementationNotFoundException(message);
    }

    static TimeoutsImplementationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new TimeoutsImplementationNotFoundException(message, cause);
    }

}
