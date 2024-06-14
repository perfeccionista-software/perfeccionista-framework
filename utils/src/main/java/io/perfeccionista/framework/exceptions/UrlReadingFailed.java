package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.UrlReadingFailedException;
import org.jetbrains.annotations.NotNull;

public interface UrlReadingFailed extends Reason {

    static UrlReadingFailedException exception(@NotNull String message) {
        return new UrlReadingFailedException(message);
    }

    static UrlReadingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new UrlReadingFailedException(message, cause);
    }

}
