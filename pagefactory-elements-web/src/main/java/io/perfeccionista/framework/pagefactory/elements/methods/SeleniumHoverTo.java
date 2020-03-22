package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.HOVER;

public class SeleniumHoverTo implements WebElementMethodImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(HOVER), getWebElementFunction);
        WebDriver webDriver = element.getDriverInstance().getDriver();
        WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
        if (withOutOfBounds) {
            Bounds bounds = element.getBounds();

            double xShift = -(bounds.getScreenX() - 10);
            double yShift = 0;

            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).map(() ->
                    new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());

        }
        element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).map(() ->
                new Actions(webDriver).moveToElement(webElement).build().perform());
        return null;
    }

}
