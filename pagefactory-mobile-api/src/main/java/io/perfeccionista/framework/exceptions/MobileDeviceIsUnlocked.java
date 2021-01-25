package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceIsUnlocked extends Reason {

    static MobileDeviceIsUnlockedAssertionError assertionError(@NotNull String message) {
        return new MobileDeviceIsUnlockedAssertionError(message);
    }

    static MobileDeviceIsUnlockedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileDeviceIsUnlockedAssertionError(message, cause);
    }

    class MobileDeviceIsUnlockedAssertionError extends PerfeccionistaAssertionError implements MobileDeviceIsUnlocked {

        MobileDeviceIsUnlockedAssertionError(String message) {
            super(message);
        }

        MobileDeviceIsUnlockedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
