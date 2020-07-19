package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;

public class SeleniumIsEnabled implements WebElementActionImplementation<Boolean> {

    @Override
    public @NotNull Boolean execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = new GetWebElement();
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(ENABLED), getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        WebElement webElement = operationResult.singleResult().get();
        return element.getWebBrowserDispatcher().getExceptionMapper()
                .map(webElement::isEnabled, element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                }).getResult();
    }

    @Override
    public Optional<JsOperation<Boolean>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }
}