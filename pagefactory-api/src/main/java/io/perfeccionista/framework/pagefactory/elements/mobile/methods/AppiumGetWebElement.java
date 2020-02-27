package io.perfeccionista.framework.pagefactory.elements.mobile.methods;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.mobile.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.locators.AppiumLocatorConverter.convertToBy;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

public class AppiumGetWebElement implements MobileElementMethodImplementation<WebElement> {

    public OperationResult<WebElement> execute(MobileChildElement element, Object... args) {
        return OperationResult.execute(() ->
                element.getDriverInstance().getExceptionMapper(SeleniumExceptionMapper.class).execute(() ->
                        element.getSearchContext().findElement(convertToBy(element.getLocator(ROOT)))));
    }

}

