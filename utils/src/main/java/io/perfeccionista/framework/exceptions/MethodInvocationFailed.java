package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.MethodInvocationFailedException;
import org.jetbrains.annotations.NotNull;

public interface MethodInvocationFailed extends Reason {

    static MethodInvocationFailedException exception(@NotNull String message) {
        return new MethodInvocationFailedException(message);
    }

    static MethodInvocationFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MethodInvocationFailedException(message, cause);
    }

}
