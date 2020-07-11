package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotSelectedException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_SELECTED;

public class AssertShouldBeSelected implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean selected = (Boolean) args[0];
        if (selected) {
            return null;
        }
        throw new ElementNotSelectedException(ELEMENT_NOT_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
    }

}
