package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;

public class JsAssertShouldNotHaveNumber implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        NumberValue<?> expectedValue = (NumberValue<?>) args[0];
        String actualValue = ((GetTextAvailable) element).getText();
        if (expectedValue.checkString(actualValue)) {
            throw new ElementTextValueException(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setType(ExceptionType.ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedValue.toString()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual value", actualValue));
        }
        return null;
    }

}

