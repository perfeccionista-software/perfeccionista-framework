package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementLabelValueException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE;

public class JsAssertShouldHaveLabelText implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        StringValue expectedValue = (StringValue) args[0];
        String actualValue = ((GetLabelAvailable) element).getLabel();
        if (expectedValue.check(actualValue)) {
            return null;
        }
        throw new ElementLabelValueException(ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                .addAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedValue.toString()))
                .addAttachmentEntry(StringAttachmentEntry.of("Actual value", actualValue));
    }

}
