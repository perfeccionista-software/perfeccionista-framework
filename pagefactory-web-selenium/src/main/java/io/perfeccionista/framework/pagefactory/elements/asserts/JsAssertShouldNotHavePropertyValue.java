package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementPropertyValueException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE;

public class JsAssertShouldNotHavePropertyValue implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebElementPropertyHolder elementPropertyHolder = (WebElementPropertyHolder) args[0];
        StringValue expectedValue = (StringValue) args[1];
        String actualValue = elementPropertyHolder.getPropertyExtractor()
                .extract(element, elementPropertyHolder.getLocatorHolder());
        if (expectedValue.check(actualValue)) {
            throw new ElementPropertyValueException(ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE.getMessage(elementPropertyHolder.getName()))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Value", expectedValue.toString()));
        }
        return null;
    }

}
