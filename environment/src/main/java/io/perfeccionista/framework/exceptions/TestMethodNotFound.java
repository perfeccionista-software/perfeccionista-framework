package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
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

    class TestMethodNotFoundException extends PerfeccionistaRuntimeException implements TestMethodNotFound {

        TestMethodNotFoundException(String message) {
            super(message);
        }

        TestMethodNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
