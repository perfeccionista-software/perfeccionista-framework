package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FixtureNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface FixtureNotFound extends Reason {

    static FixtureNotFoundException exception(@NotNull String message) {
        return new FixtureNotFoundException(message);
    }

    static FixtureNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FixtureNotFoundException(message, cause);
    }

}

