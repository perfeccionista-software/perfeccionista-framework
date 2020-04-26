package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsIsComponentDisplayed implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        LocatorHolder elementComponentLocator = (LocatorHolder) args[0];
        LocatorChain locatorChain = element.getLocatorChain().addLocator(elementComponentLocator);
        IsDisplayed isDisplayedFunction = ReflectionUtils.newInstance(IsDisplayed.class);
        JsOperation<SingleResult<Boolean>> operation = JsOperation.single(locatorChain, isDisplayedFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
