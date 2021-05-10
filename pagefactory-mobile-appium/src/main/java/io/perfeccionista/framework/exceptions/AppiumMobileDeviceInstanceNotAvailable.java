package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface AppiumMobileDeviceInstanceNotAvailable extends Reason {

    static AppiumMobileDeviceInstanceNotAvailableException exception(@NotNull String message) {
        return new AppiumMobileDeviceInstanceNotAvailableException(message);
    }

    static AppiumMobileDeviceInstanceNotAvailableException exception(@NotNull String message, @NotNull Throwable cause) {
        return new AppiumMobileDeviceInstanceNotAvailableException(message, cause);
    }

    class AppiumMobileDeviceInstanceNotAvailableException extends PerfeccionistaRuntimeException implements AppiumMobileDeviceInstanceNotAvailable {

        AppiumMobileDeviceInstanceNotAvailableException(String message) {
            super(message);
        }

        AppiumMobileDeviceInstanceNotAvailableException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
