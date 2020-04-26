package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.HOVER;

public class SeleniumHoverTo implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(HOVER), getWebElementFunction);
        WebDriver webDriver = element.getWebBrowserDispatcher().getDriver();
        WebElement webElement = element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
        if (withOutOfBounds) {
            Bounds bounds = element.getBounds(WebComponents.ROOT);

            double xShift = -(bounds.getScreenX() - 10);
            double yShift = 0;

            element.getWebBrowserDispatcher().getExceptionMapper(SeleniumExceptionMapper.class).map(() ->
                    new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());

        }
        element.getWebBrowserDispatcher().getExceptionMapper(SeleniumExceptionMapper.class).map(() ->
                new Actions(webDriver).moveToElement(webElement).build().perform());
        return null;
    }

}
