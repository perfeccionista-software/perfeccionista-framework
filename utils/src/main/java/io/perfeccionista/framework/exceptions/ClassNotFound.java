package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ClassNotFound  extends Reason {

    static ClassNotFoundException exception(@NotNull String message) {
        return new ClassNotFoundException(message);
    }

    static ClassNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassNotFoundException(message, cause);
    }

    class ClassNotFoundException extends PerfeccionistaRuntimeException implements ClassNotFound {

        ClassNotFoundException(String message) {
            super(message);
        }

        ClassNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
