package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.CommandExecutionFailed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class CommandExecutionFailedException extends PerfeccionistaRuntimeException implements CommandExecutionFailed {

    public CommandExecutionFailedException(String message) {
        super(message);
    }

    public CommandExecutionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
