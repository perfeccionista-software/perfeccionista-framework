package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.DriverJsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

public class JsGetWebElement implements ElementMethodImplementation<WebElement> {

    @Override
    public OperationResult<WebElement> execute(ChildElement element, Object... args) {
        DriverJsOperation<WebElement> operation = DriverJsOperation.of(element.getLocatorChainTo(ROOT), GetWebElement.class);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

}
