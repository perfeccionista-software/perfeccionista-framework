package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.MobileElementOperationAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MobileElementOperationHandler<T> {

    private final MobileChildElementBase element;
    private final MobileElementOperationType<T> operationType;
    private String component = null;

    private MobileElementOperationHandler(MobileChildElementBase element, MobileElementOperationType<T> operationType) {
        this.element = element;
        this.operationType = operationType;
    }

    public static <T> MobileElementOperationHandler<T> of(@NotNull MobileChildElementBase element,
                                                          @NotNull MobileElementOperationType<T> operationType) {
        return new MobileElementOperationHandler<>(element, operationType);
    }

    public static <T> MobileElementOperationHandler<T> of(@NotNull MobileChildElementBase element,
                                                          @NotNull MobileElementOperationType<T> operationType,
                                                          @Nullable String component) {
        MobileElementOperationHandler<T> operationBuilder = new MobileElementOperationHandler<>(element, operationType);
        operationBuilder.setComponent(component);
        return operationBuilder;
    }

    public MobileElementOperationHandler<T> setComponent(@Nullable String component) {
        this.component = component;
        return this;
    }

    public MobileElementOperation<T> getOperation() {
        MobileLocatorChain mobileLocatorChain = Objects.nonNull(component)
                ? element.getLocatorChainTo(component)
                : element.getLocatorChain();
        return MobileElementOperation.of(mobileLocatorChain, operationType);
    }

    public T executeGetter() {
        MobileElementOperation<T> operation = getOperation();
        operationType.getInvocationName().setMainAttachmentEntry(MobileElementOperationAttachmentEntry.of(operation));
        return element.getMobileDeviceDispatcher()
                .executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException)
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                })
                .getResult();
    }

    public void executeAction() {
        MobileElementOperation<T> operation = getOperation();
        operationType.getInvocationName().setMainAttachmentEntry(MobileElementOperationAttachmentEntry.of(operation));
        element.getMobileDeviceDispatcher()
                .executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException)
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                });
    }

}
