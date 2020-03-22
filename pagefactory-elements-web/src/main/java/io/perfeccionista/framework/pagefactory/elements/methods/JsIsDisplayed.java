package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.IsDisplayed;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsIsDisplayed implements WebElementMethodImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        LocatorChain locatorChainToElement = null == component ? element.getLocatorChain() : element.getLocatorChainTo(component);
        IsDisplayed isDisplayedFunction = ReflectionUtils.newInstance(IsDisplayed.class);
        JsOperation<SingleResult<Boolean>> operation = JsOperation.single(locatorChainToElement, isDisplayedFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
