package io.perfeccionista.framework.exceptions.js;

import org.jetbrains.annotations.NotNull;

public class ElementSearchJsException extends JsExecutionException {

    public ElementSearchJsException(@NotNull String message) {
        super(message);
    }

    public ElementSearchJsException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
