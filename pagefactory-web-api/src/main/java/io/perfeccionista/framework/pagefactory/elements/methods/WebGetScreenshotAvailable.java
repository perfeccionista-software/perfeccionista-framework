package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;

public interface WebGetScreenshotAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_SCREENSHOT_METHOD)
    @NotNull Screenshot getScreenshot();

    @WebMappedElementAction(GET_SCREENSHOT_METHOD)
    @NotNull Screenshot getScreenshot(@NotNull String componentName);

    @AssertMethodType
    WebGetScreenshotAvailable should(@NotNull WebGetScreenshotAvailableMatcher matcher);

}
