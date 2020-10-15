package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface RepeatPolicyInitialization extends Reason {

    static RepeatPolicyInitializationException exception(@NotNull String message) {
        return new RepeatPolicyInitializationException(message);
    }

    static RepeatPolicyInitializationException exception(@NotNull String message, @NotNull Throwable cause) {
        return new RepeatPolicyInitializationException(message, cause);
    }

    class RepeatPolicyInitializationException extends PerfeccionistaRuntimeException implements RepeatPolicyInitialization {

        RepeatPolicyInitializationException(String message) {
            super(message);
        }

        RepeatPolicyInitializationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}