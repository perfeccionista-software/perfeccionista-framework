package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FixtureExecutionFailedException;
import org.jetbrains.annotations.NotNull;

public interface FixtureExecutionFailed extends Reason {

    static FixtureExecutionFailedException exception(@NotNull String message) {
        return new FixtureExecutionFailedException(message);
    }

    static FixtureExecutionFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FixtureExecutionFailedException(message, cause);
    }

}

