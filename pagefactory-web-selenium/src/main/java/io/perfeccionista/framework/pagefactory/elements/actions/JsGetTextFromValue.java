package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetTextFromValue implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        GetAttribute getAttributeFunction = new GetAttribute("value");
        JsOperation<String> operation = JsOperation.of(element.getLocatorChainTo(TEXT), getAttributeFunction);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}