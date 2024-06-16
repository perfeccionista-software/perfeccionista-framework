package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.ClassCanNotBeInstantiatedException;
import org.jetbrains.annotations.NotNull;

public interface ClassCanNotBeInstantiated extends Reason {

    static ClassCanNotBeInstantiatedException exception(@NotNull String message) {
        return new ClassCanNotBeInstantiatedException(message);
    }

    static ClassCanNotBeInstantiatedException exception(@NotNull String message, @NotNull Throwable cause) {
        return new ClassCanNotBeInstantiatedException(message, cause);
    }

}
