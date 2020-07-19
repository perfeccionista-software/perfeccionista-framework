package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.HOVER;

public class SeleniumHoverTo implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(HOVER), getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        WebElement webElement = operationResult.singleResult().get();
        if (withOutOfBounds) {
            Location location = element.getLocation(WebComponents.ROOT);

            //noinspection ConstantConditions because Location from element already filled
            double xShift = -(location.getPageX() - 10);
            double yShift = 0;

            element.getWebBrowserDispatcher().getExceptionMapper().map(() ->
                    new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());

        }
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(() -> new Actions(webDriver).moveToElement(webElement).build().perform(), element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                });
        return null;
    }

    @Override
    public Optional<JsOperation<Void>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }
}
