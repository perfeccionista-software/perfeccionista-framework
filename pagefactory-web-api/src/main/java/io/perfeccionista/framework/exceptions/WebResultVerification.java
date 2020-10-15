package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebResultVerification extends Reason {

    static WebResultVerificationAssertionError assertionError(@NotNull String message) {
        return new WebResultVerificationAssertionError(message);
    }

    static WebResultVerificationAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebResultVerificationAssertionError(message, cause);
    }

    class WebResultVerificationAssertionError extends PerfeccionistaAssertionError implements WebResultVerification {

        WebResultVerificationAssertionError(String message) {
            super(message);
        }

        WebResultVerificationAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
