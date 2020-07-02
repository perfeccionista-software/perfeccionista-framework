package io.perfeccionista.framework.pagefactory.pageobjects.implementations;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class DragAndDropInteraction implements WebElementInteractionImplementation {

    @Override
    public WebChildElement execute(WebChildElement sourceElement, WebChildElement targetElement, Object... args) {
        RemoteWebDriver webDriver = sourceElement.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);

        JsOperation<WebElement> sourceOperation = JsOperation.of(sourceElement.getLocatorChainTo(ROOT), getWebElementFunction);
        WebElement sourceWebElement = sourceElement.getWebBrowserDispatcher().executor().executeOperation(sourceOperation)
                .singleResult()
                .get();
        JsOperation<WebElement> targetOperation = JsOperation.of(targetElement.getLocatorChainTo(ROOT), getWebElementFunction);
        WebElement targetWebElement = targetElement.getWebBrowserDispatcher().executor().executeOperation(targetOperation)
                .singleResult()
                .get();

        sourceElement.getWebBrowserDispatcher().getExceptionMapper()
                .map(() -> new Actions(webDriver).dragAndDrop(sourceWebElement, targetWebElement).perform());
        return sourceElement;
    }

}
