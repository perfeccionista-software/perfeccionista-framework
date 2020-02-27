package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebDropDownList;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.CLOSE;

public class SeleniumClose implements WebElementMethodImplementation<Void> {

    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        WebDropDownList dropDownList = (WebDropDownList) element;
        return OperationResult.execute(() -> {
            boolean isOpenResult = dropDownList.isOpen().getResultOrThrow();
            if (!isOpenResult) {
                return;
            }
            JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(CLOSE), GetWebElement.class);
            WebElement webElement = element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
            element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(webElement::click);
        });
    }

}
