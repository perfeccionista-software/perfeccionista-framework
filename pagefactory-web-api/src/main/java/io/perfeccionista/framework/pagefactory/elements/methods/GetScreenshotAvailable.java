package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;

public interface GetScreenshotAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_SCREENSHOT_METHOD)
    Screenshot getScreenshot(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_LOOKS_LIKE_METHOD)
    WebChildElement componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_LOOKS_LIKE_METHOD)
    WebChildElement componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

}
