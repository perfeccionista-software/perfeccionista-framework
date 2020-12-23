package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface StringValueComparatorFormatNotResolved extends Reason {

    static StringValueComparatorFormatNotResolvedException exception(@NotNull String message) {
        return new StringValueComparatorFormatNotResolvedException(message);
    }

    static StringValueComparatorFormatNotResolvedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new StringValueComparatorFormatNotResolvedException(message, cause);
    }

    class StringValueComparatorFormatNotResolvedException extends PerfeccionistaRuntimeException implements StringValueComparatorFormatNotResolved {

        StringValueComparatorFormatNotResolvedException(String message) {
            super(message);
        }

        StringValueComparatorFormatNotResolvedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
