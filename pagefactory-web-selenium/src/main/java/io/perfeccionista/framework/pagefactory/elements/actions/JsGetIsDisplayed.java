package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

public class JsGetIsDisplayed implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        locatorChainToElement.getLastLocator().setStrictSearch(false);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
