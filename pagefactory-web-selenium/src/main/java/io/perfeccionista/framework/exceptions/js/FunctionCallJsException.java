package io.perfeccionista.framework.exceptions.js;

import org.jetbrains.annotations.NotNull;

public class FunctionCallJsException extends JsExecutionException {

    public FunctionCallJsException(@NotNull String message) {
        super(message);
    }

    public FunctionCallJsException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
