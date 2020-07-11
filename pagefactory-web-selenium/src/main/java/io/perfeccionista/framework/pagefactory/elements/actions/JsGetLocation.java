package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.jsfunction.GetLocation;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

public class JsGetLocation implements WebElementActionImplementation<Location> {

    @Override
    public Location execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        GetLocation getLocationFunction = new GetLocation();
        JsOperation<Location> operation = JsOperation.of(element.getLocatorChainTo(component), getLocationFunction);
        JsOperationResult<Location> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}