package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementLocationException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION;

public class JsAssertShouldNotHaveLocation implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        Location expectedLocation = (Location) args[1];
        Location actualLocation = element.getLocation(component);
        if (expectedLocation.equals(actualLocation)) {
            throw new ElementLocationException(ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION.getMessage(component))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected location", expectedLocation.toString()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual location", actualLocation.toString()));
        }
        return null;
    }

}
