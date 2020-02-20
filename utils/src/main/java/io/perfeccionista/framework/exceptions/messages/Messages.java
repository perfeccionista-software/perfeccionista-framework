package io.perfeccionista.framework.exceptions.messages;

import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * TODO: JavaDoc
 */
public interface Messages {

    @NotNull
    default String getMessage(Object... args) {
        return format(this.getErrorMessage(), args);
    }

    @NotNull String getErrorMessage();

}
