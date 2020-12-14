package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.screenshots.Screenshot;

public interface ScreenshotParameter extends CucumberStepParameter {

    Screenshot getScreenshot();

}
