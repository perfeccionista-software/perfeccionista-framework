package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.screenshots.Screenshot;

public class ScreenshotParameterImpl implements ScreenshotParameter {

    private final Environment environment;
    private final String rawInput;

    public ScreenshotParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public Screenshot getScreenshot() {
        // TODO: Implement
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
