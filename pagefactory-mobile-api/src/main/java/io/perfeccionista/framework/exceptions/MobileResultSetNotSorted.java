package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface MobileResultSetNotSorted extends Reason {
    static MobileResultSetNotSortedAssertionError assertionError(@NotNull String message) {
        return new MobileResultSetNotSortedAssertionError(message);
    }

    static MobileResultSetNotSortedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new MobileResultSetNotSortedAssertionError(message, cause);
    }

    class MobileResultSetNotSortedAssertionError extends PerfeccionistaAssertionError implements MobileResultSetNotSorted {

        MobileResultSetNotSortedAssertionError(String message) {
            super(message);
        }

        MobileResultSetNotSortedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }
}
