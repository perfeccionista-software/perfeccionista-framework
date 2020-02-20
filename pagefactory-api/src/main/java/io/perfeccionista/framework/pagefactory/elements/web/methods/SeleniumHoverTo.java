package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.HOVER;

public class SeleniumHoverTo implements ElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(ChildElement element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        DriverJsOperation<WebElement> operation = DriverJsOperation.of(element.getLocatorChainTo(HOVER), GetWebElement.class);
        return OperationResult.execute(() -> {
            WebDriver webDriver = (WebDriver) element.getDriverInstance().getDriver();
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation)
                    .getResultOrThrow();
            if (withOutOfBounds) {
                Bounds bounds = element.getBounds().getResultOrThrow();

                double xShift = -(bounds.getScreenX() - 10);
                double yShift = 0;

                element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(() ->
                        new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());

            }
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(() ->
                    new Actions(webDriver).moveToElement(webElement).build().perform());
        });
    }

}
