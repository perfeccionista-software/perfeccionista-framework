package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.errors.StringValueNotMatchAssertionError;
import org.jetbrains.annotations.NotNull;

public interface StringValueNotMatch extends Reason {

    static StringValueNotMatchAssertionError assertionError(@NotNull String message) {
        return new StringValueNotMatchAssertionError(message);
    }

    static StringValueNotMatchAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new StringValueNotMatchAssertionError(message, cause);
    }

}
