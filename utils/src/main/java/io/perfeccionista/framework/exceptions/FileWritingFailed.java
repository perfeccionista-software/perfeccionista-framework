package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.FileWritingFailedException;
import org.jetbrains.annotations.NotNull;

public interface FileWritingFailed extends Reason {

    static FileWritingFailedException exception(@NotNull String message) {
        return new FileWritingFailedException(message);
    }

    static FileWritingFailedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileWritingFailedException(message, cause);
    }

}
