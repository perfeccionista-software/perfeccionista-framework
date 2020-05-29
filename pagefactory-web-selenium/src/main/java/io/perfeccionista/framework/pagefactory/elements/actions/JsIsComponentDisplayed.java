package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsIsComponentDisplayed implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        WebLocatorHolder elementComponentLocator = (WebLocatorHolder) args[0];
        WebLocatorChain locatorChain = element.getLocatorChain().addLocator(elementComponentLocator);
        GetIsDisplayed isDisplayedFunction = ReflectionUtils.newInstance(GetIsDisplayed.class);
        JsOperation<Boolean> operation = JsOperation.of(locatorChain, isDisplayedFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
