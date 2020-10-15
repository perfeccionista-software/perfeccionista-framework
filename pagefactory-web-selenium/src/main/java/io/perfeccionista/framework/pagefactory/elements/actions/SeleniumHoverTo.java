package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.HOVER;

public class SeleniumHoverTo implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(@NotNull WebChildElementBase element, Object... args) {
        boolean withOutOfBounds = (Boolean) args[0];
        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(HOVER), new GetWebElement());
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        WebElement webElement = operationResult.getResult();
        if (withOutOfBounds) {
            Location location = ((WebChildElement) element).getLocation(WebComponents.ROOT);

            //noinspection ConstantConditions because Location from element already filled
            double xShift = -(location.getPageX() - 10);
            double yShift = 0;

            element.getWebBrowserDispatcher().getExceptionMapper().map(() ->
                    new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform());
        }
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(() -> new Actions(webDriver).moveToElement(webElement).build().perform(), element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return null;
    }

}