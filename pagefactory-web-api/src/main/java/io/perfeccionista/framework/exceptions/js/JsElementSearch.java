package io.perfeccionista.framework.exceptions.js;

import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface JsElementSearch extends Reason {

    static JsElementSearchException exception(@NotNull String message) {
        return new JsElementSearchException(message);
    }

    static JsElementSearchException exception(@NotNull String message, @NotNull Throwable cause) {
        return new JsElementSearchException(message, cause);
    }

    class JsElementSearchException extends JsExecution.JsExecutionException implements JsElementSearch {

        JsElementSearchException(String message) {
            super(message);
        }

        JsElementSearchException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}