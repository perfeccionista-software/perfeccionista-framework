package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface ClassCanNotBeCast extends Reason {

    static ClassCanNotBeCastException exception(@NotNull String message) {
        return new ClassCanNotBeCastException(message);
    }

    static ClassCanNotBeCastException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassCanNotBeCastException(message, cause);
    }

    class ClassCanNotBeCastException extends PerfeccionistaRuntimeException implements ClassCanNotBeCast {

        ClassCanNotBeCastException(String message) {
            super(message);
        }

        ClassCanNotBeCastException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
