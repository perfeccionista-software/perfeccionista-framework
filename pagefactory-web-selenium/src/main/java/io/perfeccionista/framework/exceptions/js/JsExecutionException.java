package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class JsExecutionException extends PerfeccionistaException {

    public JsExecutionException(@NotNull String message) {
        super(message);
        setProcessed(true);
    }

    public JsExecutionException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        setProcessed(true);
    }

}
