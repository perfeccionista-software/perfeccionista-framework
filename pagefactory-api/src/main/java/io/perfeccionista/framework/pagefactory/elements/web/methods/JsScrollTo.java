package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsScrollTo implements ElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(ChildElement element, Object... args) {
        DriverJsOperation<Void> operation = DriverJsOperation.of(element.getLocatorChain(), ScrollTo.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
