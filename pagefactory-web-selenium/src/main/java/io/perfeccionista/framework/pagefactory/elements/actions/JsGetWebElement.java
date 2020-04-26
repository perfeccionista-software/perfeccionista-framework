package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class JsGetWebElement implements WebElementActionImplementation<WebElement> {

    @Override
    public WebElement execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(ROOT), getWebElementFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
