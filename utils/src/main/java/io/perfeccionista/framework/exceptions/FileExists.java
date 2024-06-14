package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.errors.FileExistsAssertionError;
import io.perfeccionista.framework.exceptions.impl.FileExistsException;
import org.jetbrains.annotations.NotNull;

public interface FileExists extends Reason {

    static FileExistsAssertionError assertionError(@NotNull String message) {
        return new FileExistsAssertionError(message);
    }

    static FileExistsAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsAssertionError(message, cause);
    }

    static FileExistsException exception(@NotNull String message) {
        return new FileExistsException(message);
    }

    static FileExistsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsException(message, cause);
    }

}
