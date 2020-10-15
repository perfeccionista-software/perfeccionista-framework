package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
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

    class TestClassNotFoundException extends PerfeccionistaRuntimeException implements TestClassNotFound {

        TestClassNotFoundException(String message) {
            super(message);
        }

        TestClassNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}