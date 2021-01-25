package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceDispatcherNotStarted extends Reason {

    static MobileDeviceDispatcherNotStartedException exception(@NotNull String message) {
        return new MobileDeviceDispatcherNotStartedException(message);
    }

    static MobileDeviceDispatcherNotStartedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new MobileDeviceDispatcherNotStartedException(message, cause);
    }

    class MobileDeviceDispatcherNotStartedException extends PerfeccionistaRuntimeException implements MobileDeviceDispatcherNotStarted {

        MobileDeviceDispatcherNotStartedException(String message) {
            super(message);
        }

        MobileDeviceDispatcherNotStartedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

