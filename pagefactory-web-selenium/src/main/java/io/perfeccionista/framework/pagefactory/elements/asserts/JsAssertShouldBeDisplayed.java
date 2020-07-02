package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;

public class JsAssertShouldBeDisplayed implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .addSuppressedException(exception)
                    .setType(ExceptionType.ASSERT)
                    .setAttachment(exception.getAttachment().orElse(Attachment.of()))
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return null;
    }

}
