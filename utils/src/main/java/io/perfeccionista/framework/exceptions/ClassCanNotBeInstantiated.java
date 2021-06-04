package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface ClassCanNotBeInstantiated extends Reason {

    static ClassCanNotBeInstantiatedException exception(@NotNull String message) {
        return new ClassCanNotBeInstantiatedException(message);
    }

    static ClassCanNotBeInstantiatedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassCanNotBeInstantiatedException(message, cause);
    }

    class ClassCanNotBeInstantiatedException extends PerfeccionistaRuntimeException implements ClassCanNotBeInstantiated {

        ClassCanNotBeInstantiatedException(String message) {
            super(message);
        }

        ClassCanNotBeInstantiatedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
