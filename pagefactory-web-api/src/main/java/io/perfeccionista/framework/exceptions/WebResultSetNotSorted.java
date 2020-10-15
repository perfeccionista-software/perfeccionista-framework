package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebResultSetNotSorted extends Reason {

    static WebResultSetNotSortedAssertionError assertionError(@NotNull String message) {
        return new WebResultSetNotSortedAssertionError(message);
    }

    static WebResultSetNotSortedAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebResultSetNotSortedAssertionError(message, cause);
    }

    class WebResultSetNotSortedAssertionError extends PerfeccionistaAssertionError implements WebResultSetNotSorted {

        WebResultSetNotSortedAssertionError(String message) {
            super(message);
        }

        WebResultSetNotSortedAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
