package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsDisabledException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IS_DISABLED;

public class AssertShouldBeEnabled implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean enabled = (Boolean) args[0];
        if (enabled) {
            return null;
        }
        throw new ElementIsDisabledException(ELEMENT_IS_DISABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
    }

    @Override
    public Optional<JsOperation<Void>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }
}
