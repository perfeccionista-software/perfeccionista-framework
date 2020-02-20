package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetText implements ElementMethodImplementation<String> {

    @Override
    public OperationResult<String> execute(ChildElement element, Object... args) {
        DriverJsOperation<String> operation = DriverJsOperation.of(element.getLocatorChainTo(TEXT), GetText.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
