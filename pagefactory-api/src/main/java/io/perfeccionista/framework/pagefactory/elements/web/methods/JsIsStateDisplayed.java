package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsIsStateDisplayed implements ElementMethodImplementation<WebChildElement, Boolean> {

    @Override
    public OperationResult<Boolean> execute(WebChildElement element, Object... args) {
        ElementStateHolder elementStateHolder = (ElementStateHolder) args[0];
        return OperationResult.execute(() -> {
            LocatorChain locatorChain = element.getLocatorChain().addLocator(elementStateHolder.getLocatorHolder());
            JsOperation<SingleResult<Boolean>> operation = JsOperation.single(locatorChain, IsDisplayed.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
