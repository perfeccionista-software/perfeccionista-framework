package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.CLICK;

public class SeleniumClick implements ElementMethodImplementation<Void> {

    public OperationResult<Void> execute(ChildElement element, Object... args) {
        DriverJsOperation<WebElement> operation = DriverJsOperation.of(element.getLocatorChainTo(CLICK), GetWebElement.class);
        return OperationResult.execute(() -> {
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation)
                    .getResultOrThrow();
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(webElement::click);
        });
    }

}
