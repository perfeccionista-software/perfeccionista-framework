package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementScreenshotException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT;

public class AssertShouldLooksLike implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        Screenshot actualScreenshot = (Screenshot) args[0];
        Screenshot expectedScreenshot = (Screenshot) args[1];
        String component = (String) args[2];
        if (expectedScreenshot.equals(actualScreenshot)) {
            return null;
        }
        throw new ElementScreenshotException(ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT.getMessage(component))
                .setType(ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                .addAttachmentEntry(ScreenshotAttachmentEntry.of("Actual screenshot", actualScreenshot))
                .addAttachmentEntry(ScreenshotAttachmentEntry.of("Expected screenshot", expectedScreenshot));
    }

}