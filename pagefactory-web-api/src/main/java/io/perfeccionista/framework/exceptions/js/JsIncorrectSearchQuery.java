package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsIncorrectSearchQuery extends Reason {

    static JsIncorrectSearchQueryException exception(@NotNull String message) {
        return new JsIncorrectSearchQueryException(message);
    }

    static JsIncorrectSearchQueryException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsIncorrectSearchQueryException(message, cause);
    }

    class JsIncorrectSearchQueryException extends JsExecution.JsExecutionException implements JsIncorrectSearchQuery {

        JsIncorrectSearchQueryException(String message) {
            super(message);
        }

        JsIncorrectSearchQueryException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
