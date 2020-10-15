package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ExtractingClipboardValue extends Reason {

    static ExtractingClipboardValueException exception(@NotNull String message) {
        return new ExtractingClipboardValueException(message);
    }

    static ExtractingClipboardValueException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ExtractingClipboardValueException(message, cause);
    }

    class ExtractingClipboardValueException extends PerfeccionistaRuntimeException implements ExtractingClipboardValue {

        ExtractingClipboardValueException(String message) {
            super(message);
        }

        ExtractingClipboardValueException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}

