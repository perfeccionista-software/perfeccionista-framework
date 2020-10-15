package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SearchContext extends Reason {

    static SearchContextException exception(@NotNull String message) {
        return new SearchContextException(message);
    }

    static SearchContextException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SearchContextException(message, cause);
    }

    class SearchContextException extends PerfeccionistaRuntimeException implements SearchContext {

        SearchContextException(String message) {
            super(message);
        }

        SearchContextException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}