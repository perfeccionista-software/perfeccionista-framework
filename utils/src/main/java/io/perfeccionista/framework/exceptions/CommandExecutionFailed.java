package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.CommandExecutionFailedException;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutionFailed extends Reason {

    static CommandExecutionFailedException exception(@NotNull String message) {
        return new CommandExecutionFailedException(message);
    }

    static CommandExecutionFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new CommandExecutionFailedException(message, cause);
    }

}

