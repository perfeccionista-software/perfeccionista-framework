package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.CLOSE;

public class SeleniumClose implements WebElementMethodImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebDropDownList dropDownList = (WebDropDownList) element;
        if (dropDownList.isOpen()) {
            GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(CLOSE), getWebElementFunction);
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).map(webElement::click);
        }
        return null;
    }

}
