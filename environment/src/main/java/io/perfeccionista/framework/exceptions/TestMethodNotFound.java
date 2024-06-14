package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.TestMethodNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface TestMethodNotFound extends Reason {

    static TestMethodNotFoundException exception(@NotNull String message) {
        return new TestMethodNotFoundException(message);
    }

    static TestMethodNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new TestMethodNotFoundException(message, cause);
    }

}
