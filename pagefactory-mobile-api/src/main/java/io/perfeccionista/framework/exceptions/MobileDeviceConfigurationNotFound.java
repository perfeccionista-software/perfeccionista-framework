package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceConfigurationNotFound extends Reason {

    static MobileDeviceConfigurationNotFoundException exception(@NotNull String message) {
        return new MobileDeviceConfigurationNotFoundException(message);
    }

    static MobileDeviceConfigurationNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MobileDeviceConfigurationNotFoundException(message, cause);
    }

    class MobileDeviceConfigurationNotFoundException extends PerfeccionistaRuntimeException implements MobileDeviceConfigurationNotFound {

        MobileDeviceConfigurationNotFoundException(String message) {
            super(message);
        }

        MobileDeviceConfigurationNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

