package io.perfeccionista.framework.exceptions.impl;

import io.perfeccionista.framework.exceptions.JsonParse;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;

public class JsonParseException extends PerfeccionistaRuntimeException implements JsonParse {

    public JsonParseException(String message) {
        super(message);
    }

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
