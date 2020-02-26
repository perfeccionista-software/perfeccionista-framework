package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

public class JsGetScreenshot implements ElementMethodImplementation<WebChildElement, Screenshot> {

    @Override
    public OperationResult<Screenshot> execute(WebChildElement element, Object... args) {
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<Screenshot>> operation = JsOperation.single(element.getLocatorChain(), GetScreenshot.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
