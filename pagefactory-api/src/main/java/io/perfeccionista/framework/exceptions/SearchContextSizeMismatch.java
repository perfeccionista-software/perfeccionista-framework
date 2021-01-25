package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SearchContextSizeMismatch extends Reason {

    static SearchContextSizeMismatchException exception(@NotNull String message) {
        return new SearchContextSizeMismatchException(message);
    }

    static SearchContextSizeMismatchException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SearchContextSizeMismatchException(message, cause);
    }

    class SearchContextSizeMismatchException extends PerfeccionistaRuntimeException implements SearchContextSizeMismatch {

        SearchContextSizeMismatchException(String message) {
            super(message);
        }

        SearchContextSizeMismatchException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
