package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_LOOKS_LIKE_METHOD;

public interface GetScreenshotAvailable extends ElementMethodAvailable {

    @MappedMethod(GET_SCREENSHOT_METHOD)
    Screenshot getScreenshot();

    @MappedMethod(SHOULD_LOOKS_LIKE_METHOD)
    GetScreenshotAvailable shouldLooksLike(Screenshot screenshot);

}
