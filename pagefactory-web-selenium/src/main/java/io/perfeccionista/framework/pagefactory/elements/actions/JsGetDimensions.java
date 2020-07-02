package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.jsfunction.GetDimensions;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

public class JsGetDimensions implements WebElementActionImplementation<Dimensions> {

    @Override
    public Dimensions execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetDimensions getDimensionsFunction = new GetDimensions();
        JsOperation<Dimensions> operation = JsOperation.of(locatorChainToElement, getDimensionsFunction);
        JsOperationResult<Dimensions> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
