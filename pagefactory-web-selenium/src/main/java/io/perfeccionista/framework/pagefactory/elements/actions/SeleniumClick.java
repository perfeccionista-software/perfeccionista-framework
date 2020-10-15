package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;

public class SeleniumClick implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(CLICK), new GetWebElement());
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        WebElement webElement = operationResult.getResult();
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(webElement::click, element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return null;
    }

}