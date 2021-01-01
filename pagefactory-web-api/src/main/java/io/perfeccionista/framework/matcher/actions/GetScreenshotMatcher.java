package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementScreenshot;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_LOOKS_LIKE_METHOD;

public class GetScreenshotMatcher implements GetScreenshotAvailableMatcher {

    private final String componentName;
    private final Screenshot expectedScreenshot;
    private final boolean positive;

    public GetScreenshotMatcher(@NotNull String componentName, @NotNull Screenshot expectedScreenshot, boolean positive) {
        this.componentName = componentName;
        this.expectedScreenshot = expectedScreenshot;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetScreenshotAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_LOOKS_LIKE_METHOD, element, componentName, expectedScreenshot)
                : assertInvocation(SHOULD_NOT_LOOKS_LIKE_METHOD, element, componentName, expectedScreenshot);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    Screenshot actualScreenshot = element.getScreenshot(componentName);
                    if (positive) {
                        shouldLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
                    } else {
                        shouldNotLooksLike(element, actualScreenshot, expectedScreenshot, componentName);
                    }
                });
    }

    protected void shouldLooksLike(GetScreenshotAvailable element, Screenshot actualScreenshot, Screenshot expectedScreenshot, String componentName) {
        if (!expectedScreenshot.equals(actualScreenshot)) {
            throw WebElementScreenshot.assertionError(ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Actual screenshot", actualScreenshot))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Expected screenshot", expectedScreenshot));
        }
    }

    protected void shouldNotLooksLike(GetScreenshotAvailable element, Screenshot actualScreenshot, Screenshot expectedScreenshot, String componentName) {
        if (expectedScreenshot.equals(actualScreenshot)) {
            throw WebElementScreenshot.assertionError(ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Actual screenshot", actualScreenshot))
                    .addLastAttachmentEntry(ScreenshotAttachmentEntry.of("Expected screenshot", expectedScreenshot));
        }
    }

}
