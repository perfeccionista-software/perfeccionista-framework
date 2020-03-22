package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.INPUT;

public class SeleniumSendKeys implements WebElementMethodImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        CharSequence[] keysToSend = (CharSequence[]) args[0];
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(INPUT), getWebElementFunction);
        WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
        element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).map(() -> webElement.sendKeys(keysToSend));
        return null;
    }

}
