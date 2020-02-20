package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsGetPropertyValue implements ElementMethodImplementation<String> {

    @Override
    public OperationResult<String> execute(ChildElement element, Object... args) {
        ElementPropertyHolder elementPropertyHolder = (ElementPropertyHolder) args[0];
        LocatorChain locatorChain = element.getLocatorChain().addLocator(elementPropertyHolder.getLocatorHolder());
        DriverJsOperation<String> operation = DriverJsOperation.of(locatorChain, elementPropertyHolder.getJsFunctionClass());
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
