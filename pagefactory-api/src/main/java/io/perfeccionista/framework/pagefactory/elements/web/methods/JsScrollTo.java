package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsScrollTo implements WebElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<Void>> operation = JsOperation.single(element.getLocatorChain(), ScrollTo.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
