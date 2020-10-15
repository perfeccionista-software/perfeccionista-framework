package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface SeleniumWebDriverBinaryNotDeclared extends Reason {

    static SeleniumWebDriverBinaryNotDeclaredException exception(@NotNull String message) {
        return new SeleniumWebDriverBinaryNotDeclaredException(message);
    }

    static SeleniumWebDriverBinaryNotDeclaredException exception(@NotNull String message, @NotNull Throwable cause) {
        return new SeleniumWebDriverBinaryNotDeclaredException(message, cause);
    }

    class SeleniumWebDriverBinaryNotDeclaredException extends PerfeccionistaRuntimeException implements SeleniumWebDriverBinaryNotDeclared {

        SeleniumWebDriverBinaryNotDeclaredException(String message) {
            super(message);
        }

        SeleniumWebDriverBinaryNotDeclaredException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
