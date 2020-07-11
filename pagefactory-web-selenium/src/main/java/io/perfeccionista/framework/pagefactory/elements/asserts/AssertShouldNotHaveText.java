package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;

public class AssertShouldNotHaveText implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String actualText = (String) args[0];
        StringValue expectedValue = (StringValue) args[1];
        if (expectedValue.check(actualText)) {
            throw new ElementTextValueException(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setType(ExceptionType.ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedValue.toString()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual value", actualText));
        }
        return null;
    }

}
