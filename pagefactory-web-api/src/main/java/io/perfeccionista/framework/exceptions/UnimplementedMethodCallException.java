package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class UnimplementedMethodCallException  extends PerfeccionistaException {

    public UnimplementedMethodCallException(@NotNull String message) {
        super(message);
    }

    public UnimplementedMethodCallException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
