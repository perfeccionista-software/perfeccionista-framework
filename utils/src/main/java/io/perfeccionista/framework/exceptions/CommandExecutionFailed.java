package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutionFailed extends Reason {

    static CommandExecutionFailedException exception(@NotNull String message) {
        return new CommandExecutionFailedException(message);
    }

    static CommandExecutionFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new CommandExecutionFailedException(message, cause);
    }

    class CommandExecutionFailedException extends PerfeccionistaRuntimeException implements CommandExecutionFailed {

        CommandExecutionFailedException(String message) {
            super(message);
        }

        CommandExecutionFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

