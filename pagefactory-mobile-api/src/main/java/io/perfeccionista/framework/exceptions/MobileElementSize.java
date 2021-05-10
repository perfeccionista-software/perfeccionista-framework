package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileElementSize extends Reason {

    static MobileElementSizeAssertionError assertionError(@NotNull String message) {
        return new MobileElementSizeAssertionError(message);
    }

    static MobileElementSizeAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileElementSizeAssertionError(message, cause);
    }

    class MobileElementSizeAssertionError extends PerfeccionistaAssertionError implements MobileElementSize {

        MobileElementSizeAssertionError(String message) {
            super(message);
        }

        MobileElementSizeAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }
}
