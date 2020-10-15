package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface RegisterDuplicate extends Reason {

    static RegisterDuplicateException exception(@NotNull String message) {
        return new RegisterDuplicateException(message);
    }

    static RegisterDuplicateException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RegisterDuplicateException(message, cause);
    }

    class RegisterDuplicateException extends PerfeccionistaRuntimeException implements RegisterDuplicate {

        RegisterDuplicateException(String message) {
            super(message);
        }

        RegisterDuplicateException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}