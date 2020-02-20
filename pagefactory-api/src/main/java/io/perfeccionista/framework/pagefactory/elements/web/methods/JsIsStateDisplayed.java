package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class JsIsStateDisplayed implements ElementMethodImplementation<Boolean> {

    @Override
    public OperationResult<Boolean> execute(ChildElement element, Object... args) {
        ElementStateHolder elementStateHolder = (ElementStateHolder) args[0];
        LocatorChain locatorChain = element.getLocatorChain().addLocator(elementStateHolder.getLocatorHolder());
        DriverJsOperation<Boolean> operation = DriverJsOperation.of(locatorChain, IsDisplayed.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
