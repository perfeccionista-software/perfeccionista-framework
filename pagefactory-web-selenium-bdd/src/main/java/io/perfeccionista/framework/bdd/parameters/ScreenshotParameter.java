package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

public interface ScreenshotParameter extends BddStepParameter {

    Screenshot getScreenshot();

}
