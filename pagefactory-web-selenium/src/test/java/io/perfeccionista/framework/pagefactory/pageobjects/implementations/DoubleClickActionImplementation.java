package io.perfeccionista.framework.pagefactory.pageobjects.implementations;

import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;

public class DoubleClickActionImplementation implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElementBase element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(CLICK), getWebElementFunction);
        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);
        WebElement webElement = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(e -> {
                    throw e;
                })
                .getResult();
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(() -> new Actions(webDriver).doubleClick(webElement).perform());
        return null;
    }

}
