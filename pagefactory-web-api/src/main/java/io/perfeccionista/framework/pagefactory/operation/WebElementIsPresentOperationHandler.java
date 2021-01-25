package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebElementIsPresentOperationHandler {

    private final WebIsPresentAvailable element;
    private final WebGetIsPresentOperationType operationType;
    private String component = null;

    private WebElementIsPresentOperationHandler(WebIsPresentAvailable element, WebGetIsPresentOperationType operationType) {
        this.element = element;
        this.operationType = operationType;
    }

    public static WebElementIsPresentOperationHandler of(@NotNull WebIsPresentAvailable element,
                                                         @NotNull WebGetIsPresentOperationType operationType) {
        return new WebElementIsPresentOperationHandler(element, operationType);
    }

    public static WebElementIsPresentOperationHandler of(@NotNull WebIsPresentAvailable element,
                                                         @NotNull WebGetIsPresentOperationType operationType,
                                                         @Nullable String component) {
        WebElementIsPresentOperationHandler operationBuilder = new WebElementIsPresentOperationHandler(element, operationType);
        operationBuilder.setComponent(component);
        return operationBuilder;
    }

    public WebElementIsPresentOperationHandler setComponent(@Nullable String component) {
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
            // Мы не нашли ни одного элемента. Ошибки нет из-за того, что операция поиска не строгая
            return operationResult.getResults().size() != 0;
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
