package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ENABLED;

public class SeleniumIsEnabled implements WebElementMethodImplementation<Boolean> {

    @Override
    public Boolean execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(ENABLED), getWebElementFunction);
        WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
        return element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).map(webElement::isEnabled);
    }

}