package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.ScreenshotFormatNotResolved;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.screenshots.ScreenshotCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.SCREENSHOT_FORMAT_NOT_RESOLVED;

public class ScreenshotParameterImpl implements ScreenshotParameter {

    private final Environment environment;
    private final String rawInput;

    public ScreenshotParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull Screenshot getScreenshot() {
        return environment.getService(CucumberService.class)
                .resolveFirst(ScreenshotCucumberResolver.class, rawInput)
                .orElseThrow(() -> ScreenshotFormatNotResolved.exception(SCREENSHOT_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
