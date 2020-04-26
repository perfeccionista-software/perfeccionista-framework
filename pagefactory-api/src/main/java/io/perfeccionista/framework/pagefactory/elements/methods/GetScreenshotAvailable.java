package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;

public interface GetScreenshotAvailable extends Element {

    @MappedElementAction(GET_SCREENSHOT_METHOD)
    Screenshot getScreenshot(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_LOOKS_LIKE_METHOD)
    GetScreenshotAvailable componentShouldLooksLike(String componentName, Screenshot screenshot);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_LOOKS_LIKE_METHOD)
    GetScreenshotAvailable componentShouldNotLooksLike(String componentName, Screenshot screenshot);

}
