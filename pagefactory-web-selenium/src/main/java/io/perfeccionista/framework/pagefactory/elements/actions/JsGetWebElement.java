package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class JsGetWebElement implements WebElementActionImplementation<WebElement> {

    @Override
    public @NotNull WebElement execute(WebChildElement element, Object... args) {
        // TODO: Тут логично еще добавить локатор для которого от корня нужно найти вебэлемент
        GetWebElement getWebElementFunction = new GetWebElement();
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChain(), getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

    @Override
    public Optional<JsOperation<WebElement>> getJsOperation(WebChildElement element, Object... args) {
        // TODO: Тут логично еще добавить локатор для которого от корня нужно найти вебэлемент
        GetWebElement getWebElementFunction = new GetWebElement();
        return Optional.of(JsOperation.of(element.getLocatorChain(), getWebElementFunction));
    }

}
