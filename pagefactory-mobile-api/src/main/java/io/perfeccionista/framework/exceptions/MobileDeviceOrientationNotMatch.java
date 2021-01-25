package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceOrientationNotMatch extends Reason {

    static MobileDeviceOrientationNotMatchAssertionError assertionError(@NotNull String message) {
        return new MobileDeviceOrientationNotMatchAssertionError(message);
    }

    static MobileDeviceOrientationNotMatchAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileDeviceOrientationNotMatchAssertionError(message, cause);
    }

    class MobileDeviceOrientationNotMatchAssertionError extends PerfeccionistaAssertionError implements MobileDeviceOrientationNotMatch {

        MobileDeviceOrientationNotMatchAssertionError(String message) {
            super(message);
        }

        MobileDeviceOrientationNotMatchAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
