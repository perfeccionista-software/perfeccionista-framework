package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_IN_FOCUS;

/**
 * Аргументом в массиве передается имя компонента для которого вычисляется признак отображения: FOCUS
 */
public class AssertShouldBeInFocus implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean inFocus = (Boolean) args[0];
        if (inFocus) {
            return null;
        }
        throw new ElementNotInFocusException(ELEMENT_NOT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
    }

}