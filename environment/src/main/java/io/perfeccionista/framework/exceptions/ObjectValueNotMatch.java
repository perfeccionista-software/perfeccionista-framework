package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.errors.ObjectValueNotMatchAssertionError;
import org.jetbrains.annotations.NotNull;

public interface ObjectValueNotMatch extends Reason {

    static ObjectValueNotMatchAssertionError assertionError(@NotNull String message) {
        return new ObjectValueNotMatchAssertionError(message);
    }

    static ObjectValueNotMatchAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ObjectValueNotMatchAssertionError(message, cause);
    }

}
