package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FixtureNotParametrized extends Reason {

    static FixtureNotParametrizedException exception(@NotNull String message) {
        return new FixtureNotParametrizedException(message);
    }

    static FixtureNotParametrizedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FixtureNotParametrizedException(message, cause);
    }

    class FixtureNotParametrizedException extends PerfeccionistaRuntimeException implements FixtureNotParametrized {

        FixtureNotParametrizedException(String message) {
            super(message);
        }

        FixtureNotParametrizedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
