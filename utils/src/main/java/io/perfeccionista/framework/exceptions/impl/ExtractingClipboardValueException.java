package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.ExtractingClipboardValue;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class ExtractingClipboardValueException extends PerfeccionistaRuntimeException implements ExtractingClipboardValue {

    public ExtractingClipboardValueException(String message) {
        super(message);
    }

    public ExtractingClipboardValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
