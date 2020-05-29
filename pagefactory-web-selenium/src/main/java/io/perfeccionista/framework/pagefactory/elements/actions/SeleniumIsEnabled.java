package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;

public class SeleniumIsEnabled implements WebElementActionImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(ENABLED), getWebElementFunction);
        WebElement webElement = element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
        return element.getWebBrowserDispatcher().getExceptionMapper().map(webElement::isEnabled);
    }

}