package io.perfeccionista.framework.exceptions.js;

import org.jetbrains.annotations.NotNull;

public class ElementStateJsException extends JsExecutionException {

    public ElementStateJsException(@NotNull String message) {
        super(message);
    }

    public ElementStateJsException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

}
