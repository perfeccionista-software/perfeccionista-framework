package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.base.UnclassifiedPerfeccionistaException;
import org.jetbrains.annotations.NotNull;

public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static <T extends Throwable> T throwIfUnhandled(@NotNull T throwable) {
        if (throwable instanceof OutOfMemoryError) {
            throw new UnclassifiedPerfeccionistaException(throwable.getMessage(), throwable);
        }
        return throwable;
    }

}
