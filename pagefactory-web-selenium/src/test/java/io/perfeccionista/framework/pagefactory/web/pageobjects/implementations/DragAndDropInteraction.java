package io.perfeccionista.framework.pagefactory.web.pageobjects.implementations;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetWebElement;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.Components.ROOT;

public class DragAndDropInteraction implements WebElementInteractionImplementation<Void> {

    @Override
    public Void execute(WebChildElement sourceElement, WebChildElement targetElement, Object... args) {
        RemoteWebDriver webDriver = sourceElement.getWebBrowserDispatcher().getDriver();
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);

        JsOperation<SingleResult<WebElement>> sourceOperation = JsOperation.single(sourceElement.getLocatorChainTo(ROOT), getWebElementFunction);
        WebElement sourceWebElement = sourceElement.getWebBrowserDispatcher().getDriverOperationExecutor()
                .executeOperation(sourceOperation).get();
        JsOperation<SingleResult<WebElement>> targetOperation = JsOperation.single(targetElement.getLocatorChainTo(ROOT), getWebElementFunction);
        WebElement targetWebElement = targetElement.getWebBrowserDispatcher().getDriverOperationExecutor()
                .executeOperation(targetOperation).get();

        sourceElement.getWebBrowserDispatcher().getExceptionMapper(SeleniumExceptionMapper.class)
                .map(() -> new Actions(webDriver).dragAndDrop(sourceWebElement, targetWebElement).perform());
        return null;
    }

}
