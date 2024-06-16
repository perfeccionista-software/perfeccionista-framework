package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ExtractingClipboardValueException;
import org.jetbrains.annotations.NotNull;

public interface ExtractingClipboardValue extends Reason {

    static ExtractingClipboardValueException exception(@NotNull String message) {
        return new ExtractingClipboardValueException(message);
    }

    static ExtractingClipboardValueException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ExtractingClipboardValueException(message, cause);
    }

}

