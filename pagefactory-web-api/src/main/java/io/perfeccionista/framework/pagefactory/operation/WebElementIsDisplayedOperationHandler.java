package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebElementIsDisplayedOperationHandler {

    private final WebIsDisplayedAvailable element;
    private final WebGetIsDisplayedOperationType operationType;
    private String component = null;

    private WebElementIsDisplayedOperationHandler(WebIsDisplayedAvailable element, WebGetIsDisplayedOperationType operationType) {
        this.element = element;
        this.operationType = operationType;
    }

    public static WebElementIsDisplayedOperationHandler of(@NotNull WebIsDisplayedAvailable element,
                                                           @NotNull WebGetIsDisplayedOperationType operationType) {
        return new WebElementIsDisplayedOperationHandler(element, operationType);
    }

    public static WebElementIsDisplayedOperationHandler of(@NotNull WebIsDisplayedAvailable element,
                                                           @NotNull WebGetIsDisplayedOperationType operationType,
                                                           @Nullable String component) {
        WebElementIsDisplayedOperationHandler operationBuilder = new WebElementIsDisplayedOperationHandler(element, operationType);
        operationBuilder.setComponent(component);
        return operationBuilder;
    }

    public WebElementIsDisplayedOperationHandler setComponent(@Nullable String component) {
        this.component = component;
        return this;
    }

    public WebElementOperation<Boolean> getOperation() {
        WebLocatorChain webLocatorChain = Objects.nonNull(component)
                ? element.getLocatorChainTo(component)
                : element.getLocatorChain();
        return WebElementOperation.of(webLocatorChain, operationType);
    }

    public boolean executeGetter() {
        WebElementOperation<Boolean> operation = getOperation();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation);
        if (operationResult.isSuccess()) {
            int resultSize = operationResult.getResults().size();
            if (resultSize == 0) {
                // Мы не нашли ни одного элемента. Ошибки нет из-за того, что операция поиска не строгая
                return false;
            }
            return operationResult.getNotNullResult();
        }
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            // Мы можем получить эксепшн при поиске родительского элемента, если для проверки отображения задан отдельный локатор.
            // В этом случае мы также не должны падать по ошибке
            if (!(exception instanceof JsElementSearch)) {
                throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
        });
        // Мы упали по ошибке ElementSearchJsException, то есть не нашли ни одного элемента. Возвращаем false
        return false;
    }

}
