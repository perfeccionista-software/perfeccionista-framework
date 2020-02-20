package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ENABLED;

public class SeleniumIsEnabled implements ElementMethodImplementation<Boolean> {

    public OperationResult<Boolean> execute(ChildElement element, Object... args) {
        DriverJsOperation<WebElement> operation = DriverJsOperation.of(element.getLocatorChainTo(ENABLED), GetWebElement.class);
        return OperationResult.execute(() -> {
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation)
                    .getResultOrThrow();
            return element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(webElement::isEnabled);
        });
    }

}