package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsIsDisplayed implements ElementMethodImplementation<WebChildElement, Boolean> {

    @Override
    public OperationResult<Boolean> execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        return OperationResult.execute(() -> {
            LocatorChain locatorChainToElement = null == component ? element.getLocatorChain() : element.getLocatorChainTo(component);
            JsOperation<SingleResult<Boolean>> operation = JsOperation.single(locatorChainToElement, IsDisplayed.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
