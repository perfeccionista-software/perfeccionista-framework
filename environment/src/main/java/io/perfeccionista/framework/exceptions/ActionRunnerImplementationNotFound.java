package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
@Deprecated
public interface ActionRunnerImplementationNotFound extends Reason {

    static ActionRunnerImplementationNotFoundException exception(@NotNull String message) {
        return new ActionRunnerImplementationNotFoundException(message);
    }

    static ActionRunnerImplementationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ActionRunnerImplementationNotFoundException(message, cause);
    }

    class ActionRunnerImplementationNotFoundException extends PerfeccionistaRuntimeException implements ActionRunnerImplementationNotFound {

        ActionRunnerImplementationNotFoundException(String message) {
            super(message);
        }

        ActionRunnerImplementationNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
