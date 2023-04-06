package io.perfeccionista.framework.conditions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;

public final class WebElementConditionExceptionHandler {

    private final boolean success;
    private final Throwable exception;

    public WebElementConditionExceptionHandler(boolean success, Throwable exception) {
        this.success = success;
        this.exception = exception;
    }

    public static WebElementConditionExceptionHandler of(@NotNull final Runnable runnable) {
        try {
            runnable.run();
            return new WebElementConditionExceptionHandler(true, null);
        } catch (PerfeccionistaAssertionError | PerfeccionistaRuntimeException exception) {
            return new WebElementConditionExceptionHandler(false, exception);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public Throwable getException() {
        return exception;
    }

}
