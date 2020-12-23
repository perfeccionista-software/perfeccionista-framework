package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

public interface ScreenshotParameter extends CucumberStepDefinitionParameter {

    @NotNull Screenshot getScreenshot();

}
