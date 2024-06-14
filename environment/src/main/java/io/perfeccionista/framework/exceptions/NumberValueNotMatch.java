package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.errors.NumberValueNotMatchAssertionError;
import org.jetbrains.annotations.NotNull;

public interface NumberValueNotMatch extends Reason {

    static NumberValueNotMatchAssertionError assertionError(@NotNull String message) {
        return new NumberValueNotMatchAssertionError(message);
    }

    static NumberValueNotMatchAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new NumberValueNotMatchAssertionError(message, cause);
    }

}
