package io.perfeccionista.framework.pagefactory.web.pageobjects.implementations;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.Components.CLICK;

public class DoubleClickActionImplementation implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<SingleResult<WebElement>> operation = JsOperation.single(element.getLocatorChainTo(CLICK), getWebElementFunction);
        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getDriver();
        WebElement webElement = element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
        element.getWebBrowserDispatcher().getExceptionMapper(SeleniumExceptionMapper.class)
                .map(() -> new Actions(webDriver).doubleClick(webElement).perform());
        return null;
    }

}
