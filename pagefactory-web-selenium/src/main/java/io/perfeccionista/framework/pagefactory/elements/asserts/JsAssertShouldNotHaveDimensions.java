package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS;

public class JsAssertShouldNotHaveDimensions implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        Dimensions expectedDimensions = (Dimensions) args[1];
        Dimensions actualDimensions = element.getDimensions(component);
        if (expectedDimensions.equals(actualDimensions)) {
            throw new ElementDimensionsException(ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS.getMessage(component))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected dimensions", expectedDimensions.toString()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual dimensions", actualDimensions.toString()));
        }
        return null;
    }

}
