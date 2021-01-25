package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceIsLocked extends Reason {

    static MobileDeviceIsLockedAssertionError assertionError(@NotNull String message) {
        return new MobileDeviceIsLockedAssertionError(message);
    }

    static MobileDeviceIsLockedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileDeviceIsLockedAssertionError(message, cause);
    }

    class MobileDeviceIsLockedAssertionError extends PerfeccionistaAssertionError implements MobileDeviceIsLocked {

        MobileDeviceIsLockedAssertionError(String message) {
            super(message);
        }

        MobileDeviceIsLockedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

