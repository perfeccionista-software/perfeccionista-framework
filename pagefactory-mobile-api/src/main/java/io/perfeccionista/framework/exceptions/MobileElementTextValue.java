package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileElementTextValue extends Reason {

    static MobileElementTextValueAssertionError assertionError(@NotNull String message) {
        return new MobileElementTextValueAssertionError(message);
    }

    static MobileElementTextValueAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileElementTextValueAssertionError(message, cause);
    }

    class MobileElementTextValueAssertionError extends PerfeccionistaAssertionError implements MobileElementTextValue {

        MobileElementTextValueAssertionError(String message) {
            super(message);
        }

        MobileElementTextValueAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }
}
