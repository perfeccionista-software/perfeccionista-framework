package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ClassCanNotBeCastException;
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

}
