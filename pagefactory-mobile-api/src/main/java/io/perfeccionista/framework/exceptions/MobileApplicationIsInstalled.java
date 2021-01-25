package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileApplicationIsInstalled extends Reason {

    static MobileApplicationIsInstalledAssertionError assertionError(@NotNull String message) {
        return new MobileApplicationIsInstalledAssertionError(message);
    }

    static MobileApplicationIsInstalledAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileApplicationIsInstalledAssertionError(message, cause);
    }

    class MobileApplicationIsInstalledAssertionError extends PerfeccionistaAssertionError implements MobileApplicationIsInstalled {

        MobileApplicationIsInstalledAssertionError(String message) {
            super(message);
        }

        MobileApplicationIsInstalledAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
