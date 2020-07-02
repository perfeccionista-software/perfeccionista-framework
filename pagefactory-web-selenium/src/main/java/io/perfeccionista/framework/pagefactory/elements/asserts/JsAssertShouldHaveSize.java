package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_SIZE_NOT_MATCH;

public class JsAssertShouldHaveSize implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        NumberValue<Integer> expectedValue = (NumberValue<Integer>) args[0];
        int actualSize = ((SizeAvailable) element).size();
        if (expectedValue.check(actualSize)) {
            return null;
        }
        throw new ElementSizeException(ELEMENT_SIZE_NOT_MATCH.getMessage())
                .setType(ExceptionType.ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                .addAttachmentEntry(StringAttachmentEntry.of("Expected size", expectedValue.toString()))
                .addAttachmentEntry(StringAttachmentEntry.of("Actual size", String.valueOf(actualSize)));
    }

}
