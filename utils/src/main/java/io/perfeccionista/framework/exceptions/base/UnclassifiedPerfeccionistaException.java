package io.perfeccionista.framework.exceptions.base;

import org.jetbrains.annotations.NotNull;

public class UnclassifiedPerfeccionistaException extends PerfeccionistaRuntimeException {

    public UnclassifiedPerfeccionistaException(@NotNull String message) {
        super(message);
    }

    public UnclassifiedPerfeccionistaException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
