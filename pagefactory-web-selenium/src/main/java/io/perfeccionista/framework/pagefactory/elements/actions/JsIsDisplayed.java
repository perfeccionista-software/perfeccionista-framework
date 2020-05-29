package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsIsDisplayed implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = null == component ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetIsDisplayed isDisplayedFunction = ReflectionUtils.newInstance(GetIsDisplayed.class);
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
