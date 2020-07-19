package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.js.ElementSearchJsException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class JsGetIsDisplayed implements WebElementActionImplementation<Boolean> {

    @Override
    public @NotNull Boolean execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(component);
        locatorChainToElement.getLastLocator().setStrictSearch(false);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        if (operationResult.isSuccess()) {
            int resultSize = operationResult.multipleResult().getSize();
            if (resultSize == 0) {
                // Мы не нашли ни одного элемента. Ошибки нет из-за того, что операция поиска не строгая
                return false;
            }
            return operationResult.singleResult().get();
        }
        operationResult.ifException(exception -> {
            // Мы можем получить эксепшн при поиске родительского элемента, если для проверки отображения задан отдельный локатор.
            // В этом случае мы также не должны падать по ошибке
            if (!ElementSearchJsException.class.equals(exception.getClass())) {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
        // Мы упали по ошибке ElementSearchJsException, то есть не нашли ни одного элемента. Возвращаем false
        return false;
    }

    @Override
    public Optional<JsOperation<Boolean>> getJsOperation(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(component);
        locatorChainToElement.getLastLocator().setStrictSearch(false);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        return Optional.of(JsOperation.of(locatorChainToElement, isDisplayedFunction));
    }

}
