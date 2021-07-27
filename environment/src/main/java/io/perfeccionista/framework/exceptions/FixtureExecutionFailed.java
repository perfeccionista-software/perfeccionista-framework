package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface FixtureExecutionFailed extends Reason {

    static FixtureExecutionFailedException exception(@NotNull String message) {
        return new FixtureExecutionFailedException(message);
    }

    static FixtureExecutionFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FixtureExecutionFailedException(message, cause);
    }

    class FixtureExecutionFailedException extends PerfeccionistaRuntimeException implements FixtureExecutionFailed {

        FixtureExecutionFailedException(String message) {
            super(message);
        }

        FixtureExecutionFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

