package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementInFocusException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IN_FOCUS;

public class JsAssertShouldNotBeInFocus implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        if (element.isInFocus()) {
            throw new ElementInFocusException(ELEMENT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setType(ExceptionType.ASSERT)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
        return null;
    }

}

