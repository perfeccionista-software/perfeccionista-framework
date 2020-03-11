package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

public class JsGetWebElement implements WebElementMethodImplementation<WebElement> {

    @Override
    public OperationResult<WebElement> execute(WebChildElement element, Object... args) {
        return OperationResult.of(() -> {
            GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(ROOT), getWebElementFunction);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
