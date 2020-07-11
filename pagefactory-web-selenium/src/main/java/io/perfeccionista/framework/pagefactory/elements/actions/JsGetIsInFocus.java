package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsInFocus;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.FOCUS;

public class JsGetIsInFocus  implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(FOCUS);
        GetIsInFocus getIsInFocusFunction = new GetIsInFocus();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, getIsInFocusFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}