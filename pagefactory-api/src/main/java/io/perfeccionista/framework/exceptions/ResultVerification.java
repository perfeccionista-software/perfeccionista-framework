package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ResultVerification extends Reason {

    static ResultVerificationAssertionError assertionError(@NotNull String message) {
        return new ResultVerificationAssertionError(message);
    }

    static ResultVerificationAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new ResultVerificationAssertionError(message, cause);
    }

    class ResultVerificationAssertionError extends PerfeccionistaAssertionError implements ResultVerification {

        ResultVerificationAssertionError(String message) {
            super(message);
        }

        ResultVerificationAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
