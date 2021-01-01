package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

public class JsGetIsPresent implements WebElementJsOperationActionImplementation<Boolean> {

    @Override
    public @NotNull Boolean execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Boolean> operation = getJsOperation(element, args);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation);
        if (operationResult.isSuccess()) {
            // Мы не нашли ни одного элемента. Ошибки нет из-за того, что операция поиска не строгая
            return operationResult.getResults().size() != 0;
        }
        operationResult.ifException(exception -> {
            // Мы можем получить эксепшн при поиске родительского элемента, если для проверки отображения задан отдельный локатор.
            // В этом случае мы также не должны падать по ошибке
            if (!(exception instanceof JsElementSearch)) {
                throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
        });
        // Мы упали по ошибке ElementSearchJsException, то есть не нашли ни одного элемента. Возвращаем false
        return false;
    }

    @Override
    public @NotNull JsOperation<Boolean> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        String componentName = (String) args[0];
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName)
                .updateLastLocator(locator -> locator.setStrictSearch(false));
        return JsOperation.of(locatorChainToElement, new GetIsPresent());
    }

}