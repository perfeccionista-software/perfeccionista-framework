package io.perfeccionista.framework.preconditions;

import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.exceptions.PreconditionViolation.PreconditionViolationException;

public class Preconditions {

    public static <T> T notNull(T object, String message) throws PreconditionViolationException {
        condition(object != null, message);
        return object;
    }

    public static void condition(boolean predicate, String message) throws PreconditionViolationException {
        if (!predicate) {
            throw PreconditionViolation.exception(message);
        }
    }

}
