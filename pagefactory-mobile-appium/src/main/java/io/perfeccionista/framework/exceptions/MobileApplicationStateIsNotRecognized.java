package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileApplicationStateIsNotRecognized extends Reason {

    static MobileApplicationStateIsNotRecognizedException exception(@NotNull String message) {
        return new MobileApplicationStateIsNotRecognizedException(message);
    }

    static MobileApplicationStateIsNotRecognizedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MobileApplicationStateIsNotRecognizedException(message, cause);
    }

    class MobileApplicationStateIsNotRecognizedException extends PerfeccionistaRuntimeException implements MobileApplicationStateIsNotRecognized {

        MobileApplicationStateIsNotRecognizedException(String message) {
            super(message);
        }

        MobileApplicationStateIsNotRecognizedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
