package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FixtureNotFound extends Reason {

    static FixtureNotFoundException exception(@NotNull String message) {
        return new FixtureNotFoundException(message);
    }

    static FixtureNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FixtureNotFoundException(message, cause);
    }

    class FixtureNotFoundException extends PerfeccionistaRuntimeException implements FixtureNotFound {

        FixtureNotFoundException(String message) {
            super(message);
        }

        FixtureNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

