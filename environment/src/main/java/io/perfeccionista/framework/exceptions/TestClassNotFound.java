package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.TestClassNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: JavaDoc
 */
public interface TestClassNotFound extends Reason {

    static TestClassNotFoundException exception(@NotNull String message) {
        return new TestClassNotFoundException(message);
    }

    static TestClassNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new TestClassNotFoundException(message, cause);
    }

}
