package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface AppiumMobileDeviceInstanceNotStarted extends Reason {

    static AppiumMobileDeviceInstanceNotStartedException exception(@NotNull String message) {
        return new AppiumMobileDeviceInstanceNotStartedException(message);
    }

    static AppiumMobileDeviceInstanceNotStartedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new AppiumMobileDeviceInstanceNotStartedException(message, cause);
    }

    class AppiumMobileDeviceInstanceNotStartedException extends PerfeccionistaRuntimeException implements AppiumMobileDeviceInstanceNotStarted {

        AppiumMobileDeviceInstanceNotStartedException(String message) {
            super(message);
        }

        AppiumMobileDeviceInstanceNotStartedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
