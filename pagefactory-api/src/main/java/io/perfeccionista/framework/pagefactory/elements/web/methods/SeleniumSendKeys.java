package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.INPUT;

public class SeleniumSendKeys implements WebElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        CharSequence[] keysToSend = (CharSequence[]) args[0];
        return OperationResult.execute(() -> {
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(INPUT), GetWebElement.class);
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(() -> webElement.sendKeys(keysToSend));
        });
    }

}
