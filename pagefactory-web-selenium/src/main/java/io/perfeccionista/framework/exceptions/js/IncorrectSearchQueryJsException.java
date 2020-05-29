package io.perfeccionista.framework.exceptions.js;

import org.jetbrains.annotations.NotNull;

public class IncorrectSearchQueryJsException extends JsExecutionException {

    public IncorrectSearchQueryJsException(@NotNull String message) {
        super(message);
    }

    public IncorrectSearchQueryJsException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
