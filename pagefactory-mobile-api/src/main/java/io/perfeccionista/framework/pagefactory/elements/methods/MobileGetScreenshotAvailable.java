package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;

public interface MobileGetScreenshotAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(GET_SCREENSHOT_METHOD)
    @NotNull Screenshot getScreenshot();

    @MobileMappedElementAction(GET_SCREENSHOT_METHOD)
    @NotNull Screenshot getScreenshot(@NotNull String componentName);

    @AssertMethodType
    MobileGetScreenshotAvailable should(@NotNull MobileGetScreenshotAvailableMatcher matcher);

}
