package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementScreenshot;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.options.GetScreenshotOptions.screenshotForComponent;

public class WebShouldLooksLikeElementCondition implements WebElementCondition {

    private String componentName;
    private final Screenshot expectedScreenshot;
    private boolean positive;

    public WebShouldLooksLikeElementCondition(@NotNull Screenshot expectedScreenshot) {
        this.componentName = ROOT;
        this.expectedScreenshot = expectedScreenshot;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        Screenshot actualScreenshot = element.getScreenshot(screenshotForComponent(componentName));
        if (positive) {
            shouldLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
        } else {
            shouldNotLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_LOOKS_LIKE_METHOD, elementName, componentName, String.valueOf(expectedScreenshot.getSize()))
                : assertInvocation(SHOULD_NOT_LOOKS_LIKE_METHOD, elementName, componentName, String.valueOf(expectedScreenshot.getSize()));
    }

    @Override
    public WebShouldLooksLikeElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebGetScreenshotAvailable interface
        return this;
    }

    @Override
    public WebShouldLooksLikeElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldLooksLikeElementCondition inverse() {
        this.positive = !positive;
        return this;
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
