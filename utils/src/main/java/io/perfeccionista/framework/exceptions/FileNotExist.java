package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.errors.FileNotExistAssertionError;
import io.perfeccionista.framework.exceptions.impl.FileNotExistException;
import org.jetbrains.annotations.NotNull;

public interface FileNotExist extends Reason {

    static FileNotExistAssertionError assertionError(@NotNull String message) {
        return new FileNotExistAssertionError(message);
    }

    static FileNotExistAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new FileNotExistAssertionError(message, cause);
    }

    static FileNotExistException exception(@NotNull String message) {
        return new FileNotExistException(message);
    }

    static FileNotExistException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileNotExistException(message, cause);
    }

}
