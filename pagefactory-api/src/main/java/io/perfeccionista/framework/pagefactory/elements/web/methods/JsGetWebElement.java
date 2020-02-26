package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

public class JsGetWebElement implements ElementMethodImplementation<WebChildElement, WebElement> {

    @Override
    public OperationResult<WebElement> execute(WebChildElement element, Object... args) {
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(ROOT), GetWebElement.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
