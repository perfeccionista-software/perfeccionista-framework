package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;

public interface GetScreenshotAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_SCREENSHOT_METHOD)
    @NotNull Screenshot getScreenshot(@NotNull String componentName);

    @AssertMethodType
    GetScreenshotAvailable should(@NotNull GetScreenshotAvailableMatcher matcher);

}
