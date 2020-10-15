package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.Reason;
import org.jetbrains.annotations.NotNull;

public interface WebElementScreenshot extends Reason {

    static WebElementScreenshotAssertionError assertionError(@NotNull String message) {
        return new WebElementScreenshotAssertionError(message);
    }

    static WebElementScreenshotAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new WebElementScreenshotAssertionError(message, cause);
    }

    class WebElementScreenshotAssertionError extends PerfeccionistaAssertionError implements WebElementScreenshot {

        WebElementScreenshotAssertionError(String message) {
            super(message);
        }

        WebElementScreenshotAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
