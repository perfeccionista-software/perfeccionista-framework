package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.IncorrectInvocationRunnerLogicException;
import org.jetbrains.annotations.NotNull;

public interface IncorrectInvocationRunnerLogic extends Reason {

    static IncorrectInvocationRunnerLogicException exception(@NotNull String message) {
        return new IncorrectInvocationRunnerLogicException(message);
    }

    static IncorrectInvocationRunnerLogicException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectInvocationRunnerLogicException(message, cause);
    }

}
