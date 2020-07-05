package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsInFocus;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_IN_FOCUS;

/**
 * Аргументом в массиве передается имя компонента для которого вычисляется признак отображения: FOCUS
 */
public class JsAssertShouldBeInFocus implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetIsInFocus getIsInFocusFunction = new GetIsInFocus();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, getIsInFocusFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean inFocus = operationResult.singleResult().get();
        if (inFocus) {
            return null;
        }
        throw new ElementNotInFocusException(ELEMENT_NOT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
    }

}