package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsFunctionCall extends Reason {

    static JsFunctionCallException exception(@NotNull String message) {
        return new JsFunctionCallException(message);
    }

    static JsFunctionCallException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsFunctionCallException(message, cause);
    }

    class JsFunctionCallException extends JsExecution.JsExecutionException implements JsFunctionCall {

        JsFunctionCallException(String message) {
            super(message);
        }

        JsFunctionCallException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}