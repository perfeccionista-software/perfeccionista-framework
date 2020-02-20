package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.GetBounds;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class JsGetBounds implements ElementMethodImplementation<Bounds> {

    @Override
    public OperationResult<Bounds> execute(ChildElement element, Object... args) {
        DriverJsOperation<Bounds> operation = DriverJsOperation.of(element.getLocatorChainTo(TEXT), GetBounds.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
