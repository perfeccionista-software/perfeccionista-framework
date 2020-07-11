package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementPropertyValueException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_VALUE_IS_NULL;

public class AssertShouldNotHavePropertyNumber implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String actualValue = (String) args[0];
        NumberValue<?> expectedValue = (NumberValue<?>) args[1];
        String propertyName = (String) args[2];
        if (actualValue == null) {
            String elementName = element.getElementIdentifier().getLastUsedName();
            throw new ElementPropertyValueException(ELEMENT_PROPERTY_VALUE_IS_NULL.getMessage(propertyName, elementName))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Value", expectedValue.toString()));
        }
        if (expectedValue.checkString(actualValue)) {
            throw new ElementPropertyValueException(ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Value", expectedValue.toString()));
        }
        return null;
    }

}

