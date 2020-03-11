package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsIsStateDisplayed implements WebElementMethodImplementation<Boolean> {

    @Override
    public OperationResult<Boolean> execute(WebChildElement element, Object... args) {
        LocatorHolder elementStateLocator = (LocatorHolder) args[0];
        return OperationResult.of(() -> {
            LocatorChain locatorChain = element.getLocatorChain().addLocator(elementStateLocator);
            IsDisplayed isDisplayedFunction = ReflectionUtils.newInstance(IsDisplayed.class);
            JsOperation<SingleResult<Boolean>> operation = JsOperation.single(locatorChain, isDisplayedFunction);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
