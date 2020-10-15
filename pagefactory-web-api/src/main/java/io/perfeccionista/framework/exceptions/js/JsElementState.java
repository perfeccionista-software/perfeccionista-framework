package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsElementState extends Reason {

    static JsElementStateException exception(@NotNull String message) {
        return new JsElementStateException(message);
    }

    static JsElementStateException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsElementStateException(message, cause);
    }

    class JsElementStateException extends JsExecution.JsExecutionException implements JsElementState {

        JsElementStateException(String message) {
            super(message);
        }

        JsElementStateException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
