package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetSize;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsSize implements ElementMethodImplementation<WebChildElement, Integer> {

    @Override
    public OperationResult<Integer> execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<Integer>> operation = JsOperation.single(element.getLocatorChainTo(component), GetSize.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
