package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.CLICK;

public class SeleniumSubmit implements ElementMethodImplementation<WebChildElement, Void> {

    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(CLICK), GetWebElement.class);
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(webElement::submit);
        });
    }

}
