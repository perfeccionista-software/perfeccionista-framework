package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.IncorrectFileNameException;
import org.jetbrains.annotations.NotNull;

public interface IncorrectFileName extends Reason {

    static IncorrectFileNameException exception(@NotNull String message) {
        return new IncorrectFileNameException(message);
    }

    static IncorrectFileNameException exception(@NotNull String message, @NotNull Throwable cause) {
        return new IncorrectFileNameException(message, cause);
    }

}
