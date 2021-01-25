package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ObjectValueNotMatch extends Reason {

    static ObjectValueNotMatchAssertionError assertionError(@NotNull String message) {
        return new ObjectValueNotMatchAssertionError(message);
    }

    static ObjectValueNotMatchAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ObjectValueNotMatchAssertionError(message, cause);
    }

    class ObjectValueNotMatchAssertionError extends PerfeccionistaAssertionError implements ObjectValueNotMatch {

        ObjectValueNotMatchAssertionError(String message) {
            super(message);
        }

        ObjectValueNotMatchAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
