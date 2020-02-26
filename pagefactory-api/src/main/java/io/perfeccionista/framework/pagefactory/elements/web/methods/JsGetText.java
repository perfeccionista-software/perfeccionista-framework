package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetText implements ElementMethodImplementation<WebChildElement, String> {

    @Override
    public OperationResult<String> execute(WebChildElement element, Object... args) {
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<String>> operation = JsOperation.single(element.getLocatorChainTo(TEXT), GetText.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
