package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;

public class SeleniumClear implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(CLEAR), getWebElementFunction);
        WebElement webElement = element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
        element.getWebBrowserDispatcher().getExceptionMapper().map(webElement::clear);
        return null;
    }

}
