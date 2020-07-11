package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.Color;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR;

public class AssertShouldHaveColor implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        Color actualColor = (Color) args[0];
        Color expectedColor = (Color) args[1];
        String componentName = (String) args[2];
        if (expectedColor.equals(actualColor)) {
            return null;
        }
        throw new ElementColorException(ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR.getMessage(componentName))
                .setType(ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                .addAttachmentEntry(StringAttachmentEntry.of("Actual color", actualColor.toString()))
                .addAttachmentEntry(StringAttachmentEntry.of("Expected color", expectedColor.toString()));
    }

}
