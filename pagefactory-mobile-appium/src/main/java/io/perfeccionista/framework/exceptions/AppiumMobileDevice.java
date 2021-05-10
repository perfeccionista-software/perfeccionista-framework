package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface AppiumMobileDevice extends Reason {

    static AppiumMobileDeviceException exception(@NotNull String message) {
        return new AppiumMobileDeviceException(message);
    }

    static AppiumMobileDeviceException exception(@NotNull String message, @NotNull Throwable cause) {
        return new AppiumMobileDeviceException(message, cause);
    }

    class AppiumMobileDeviceException extends PerfeccionistaRuntimeException implements AppiumMobileDevice {

        AppiumMobileDeviceException(String message) {
            super(message);
        }

        AppiumMobileDeviceException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

