package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsExecution extends Reason {

    static JsExecutionException exception(@NotNull String message) {
        return new JsExecutionException(message);
    }

    static JsExecutionException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsExecutionException(message, cause);
    }

    class JsExecutionException extends PerfeccionistaRuntimeException implements JsExecution {

        JsExecutionException(String message) {
            super(message);
        }

        JsExecutionException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

