package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.operation.type.WebElementOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebElementOperationHandler<T> {

    private final WebChildElementBase element;
    private final WebElementOperationType<T> operationType;
    private String component = null;

    private WebElementOperationHandler(WebChildElementBase element, WebElementOperationType<T> operationType) {
        this.element = element;
        this.operationType = operationType;
    }

    public static <T> WebElementOperationHandler<T> of(@NotNull WebChildElementBase element,
                                                       @NotNull WebElementOperationType<T> operationType) {
        return new WebElementOperationHandler<>(element, operationType);
    }

    public static <T> WebElementOperationHandler<T> of(@NotNull WebChildElementBase element,
                                                       @NotNull WebElementOperationType<T> operationType,
                                                       @Nullable String component) {
        WebElementOperationHandler<T> operationBuilder = new WebElementOperationHandler<>(element, operationType);
        operationBuilder.setComponent(component);
        return operationBuilder;
    }

    public WebElementOperationHandler<T> setComponent(@Nullable String component) {
        this.component = component;
        return this;
    }

    public WebElementOperation<T> getOperation() {
        WebLocatorChain webLocatorChain = Objects.nonNull(component)
                ? element.getLocatorChainTo(component)
                : element.getLocatorChain();
        return WebElementOperation.of(webLocatorChain, operationType);
    }

    public @NotNull T executeGetter() {
        WebElementOperation<T> operation = getOperation();
        return element.getWebBrowserDispatcher()
                .executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                })
                .getNotNullResult();
    }

    public void executeAction() {
        WebElementOperation<T> operation = getOperation();
        element.getWebBrowserDispatcher()
                .executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
    }

}
