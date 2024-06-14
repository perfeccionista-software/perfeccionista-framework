package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class EmptyAttachmentException extends PerfeccionistaRuntimeException implements EmptyAttachment {

    public EmptyAttachmentException(String message) {
        super(message);
    }

    public EmptyAttachmentException(String message, Throwable cause) {
        super(message, cause);
    }

}
