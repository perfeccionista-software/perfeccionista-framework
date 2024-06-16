package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.IncorrectUrlException;
import org.jetbrains.annotations.NotNull;

public interface IncorrectUrl extends Reason {

    static IncorrectUrlException exception(@NotNull String message) {
        return new IncorrectUrlException(message);
    }

    static IncorrectUrlException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectUrlException(message, cause);
    }

}
