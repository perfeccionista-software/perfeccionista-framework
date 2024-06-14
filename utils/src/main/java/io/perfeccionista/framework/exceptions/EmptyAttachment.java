package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.EmptyAttachmentException;
import org.jetbrains.annotations.NotNull;

public interface EmptyAttachment extends Reason {

    static EmptyAttachmentException exception(@NotNull String message) {
        return new EmptyAttachmentException(message);
    }

    static EmptyAttachmentException exception(@NotNull String message, @NotNull Throwable cause) {
        return new EmptyAttachmentException(message, cause);
    }

}
