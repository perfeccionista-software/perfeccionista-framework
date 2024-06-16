package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FileReadingFailedException;
import org.jetbrains.annotations.NotNull;

public interface FileReadingFailed extends Reason {

    static FileReadingFailedException exception(@NotNull String message) {
        return new FileReadingFailedException(message);
    }

    static FileReadingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileReadingFailedException(message, cause);
    }

}
