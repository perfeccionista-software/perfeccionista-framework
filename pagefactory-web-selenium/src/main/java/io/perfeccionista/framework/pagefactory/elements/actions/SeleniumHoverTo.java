package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.HOVER;

public class SeleniumHoverTo implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(HOVER), getWebElementFunction);
        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);
        WebElement webElement = element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
        if (withOutOfBounds) {
            Location location = element.getLocation(WebComponents.ROOT);

            double xShift = -(location.getPageX() - 10);
            double yShift = 0;

            element.getWebBrowserDispatcher().getExceptionMapper().map(() ->
                    new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());

        }
        element.getWebBrowserDispatcher().getExceptionMapper().map(() ->
                new Actions(webDriver).moveToElement(webElement).build().perform());
        return null;
    }

}
