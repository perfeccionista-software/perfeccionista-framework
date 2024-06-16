package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.Reason;
import io.perfeccionista.framework.exceptions.impl.DataSourceNotFoundException;
import org.jetbrains.annotations.NotNull;

public interface DataSourceNotFound extends Reason {

    static DataSourceNotFoundException exception(@NotNull String message) {
        return new DataSourceNotFoundException(message);
    }

    static DataSourceNotFoundException exception(@NotNull String message, @NotNull Throwable cause) {
        return new DataSourceNotFoundException(message, cause);
    }

}
