package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.OPEN;


public class SeleniumOpen implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebDropDownList dropDownList = (WebDropDownList) element;
        if (!dropDownList.isOpen()) {
            GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
            JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(OPEN), getWebElementFunction);
            WebElement webElement = element.getWebBrowserDispatcher().executor().executeOperation(operation)
                    .singleResult()
                    .get();
            element.getWebBrowserDispatcher().getExceptionMapper().map(webElement::click);
        }
        return null;
    }

}
