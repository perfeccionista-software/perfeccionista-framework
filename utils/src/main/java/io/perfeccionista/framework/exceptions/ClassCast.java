package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
@Deprecated
public interface ClassCast extends Reason {

    static ClassCastException exception(@NotNull String message) {
        return new ClassCastException(message);
    }

    static ClassCastException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassCastException(message, cause);
    }

    class ClassCastException extends PerfeccionistaRuntimeException implements ClassCast {

        ClassCastException(String message) {
            super(message);
        }

        ClassCastException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
