package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsGetPropertyValue implements ElementMethodImplementation<WebChildElement, String> {

    @Override
    public OperationResult<String> execute(WebChildElement element, Object... args) {
        ElementPropertyHolder elementPropertyHolder = (ElementPropertyHolder) args[0];
        return OperationResult.execute(() -> {
            LocatorChain locatorChain = element.getLocatorChain().addLocator(elementPropertyHolder.getLocatorHolder());
            JsOperation<SingleResult<String>> operation = JsOperation.single(locatorChain, elementPropertyHolder.getJsFunctionClass());
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
