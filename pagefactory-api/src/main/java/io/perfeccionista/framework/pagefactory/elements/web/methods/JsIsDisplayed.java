package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsIsDisplayed implements ElementMethodImplementation<Boolean> {

    @Override
    public OperationResult<Boolean> execute(ChildElement element, Object... args) {
        DriverJsOperation<Boolean> operation = DriverJsOperation.of(element.getLocatorChain(), IsDisplayed.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
