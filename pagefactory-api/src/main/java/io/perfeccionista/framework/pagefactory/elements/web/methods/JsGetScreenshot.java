package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

public class JsGetScreenshot implements ElementMethodImplementation<Screenshot> {

    @Override
    public OperationResult<Screenshot> execute(ChildElement element, Object... args) {
        DriverJsOperation<Screenshot> operation = DriverJsOperation.of(element.getLocatorChain(), GetScreenshot.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
