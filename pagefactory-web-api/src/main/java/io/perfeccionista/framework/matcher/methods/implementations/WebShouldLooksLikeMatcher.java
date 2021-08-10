package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementScreenshot;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_LOOKS_LIKE_METHOD;

public class WebShouldLooksLikeMatcher implements WebGetScreenshotAvailableMatcher {

    private final String componentName;
    private final Screenshot expectedScreenshot;
    private final boolean positive;

    public WebShouldLooksLikeMatcher(@NotNull String componentName, @NotNull Screenshot expectedScreenshot, boolean positive) {
        this.componentName = componentName;
        this.expectedScreenshot = expectedScreenshot;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetScreenshotAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_LOOKS_LIKE_METHOD, elementName, componentName, String.valueOf(expectedScreenshot.getSize()))
                : assertInvocation(SHOULD_NOT_LOOKS_LIKE_METHOD, elementName, componentName, String.valueOf(expectedScreenshot.getSize()));

        runCheck(invocationName,
                () -> {
                    Screenshot actualScreenshot = element.getScreenshot(componentName);
                    if (positive) {
                        shouldLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
                    } else {
                        shouldNotLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
                    }
                });
    }

    protected void shouldLooksLike(WebGetScreenshotAvailable element, Screenshot actualScreenshot, Screenshot expectedScreenshot, String componentName) {
        if (!expectedScreenshot.equals(actualScreenshot)) {
            throw WebElementScreenshot.assertionError(ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Actual screenshot", actualScreenshot))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Expected screenshot", expectedScreenshot));
        }
    }

    protected void shouldNotLooksLike(WebGetScreenshotAvailable element, Screenshot actualScreenshot, Screenshot expectedScreenshot, String componentName) {
        if (expectedScreenshot.equals(actualScreenshot)) {
            throw WebElementScreenshot.assertionError(ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Actual screenshot", actualScreenshot))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Expected screenshot", expectedScreenshot));
        }
    }

}
