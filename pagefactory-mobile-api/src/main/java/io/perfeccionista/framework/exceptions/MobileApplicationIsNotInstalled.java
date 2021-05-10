package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileApplicationIsNotInstalled extends Reason {

    static MobileApplicationIsNotInstalledAssertionError assertionError(@NotNull String message) {
        return new MobileApplicationIsNotInstalledAssertionError(message);
    }

    static MobileApplicationIsNotInstalledAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileApplicationIsNotInstalledAssertionError(message, cause);
    }

    class MobileApplicationIsNotInstalledAssertionError extends PerfeccionistaAssertionError implements MobileApplicationIsNotInstalled {

        MobileApplicationIsNotInstalledAssertionError(String message) {
            super(message);
        }

        MobileApplicationIsNotInstalledAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
