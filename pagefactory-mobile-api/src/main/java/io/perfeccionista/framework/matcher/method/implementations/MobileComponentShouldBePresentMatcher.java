package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.ElementIsPresent;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;

public class MobileComponentShouldBePresentMatcher implements MobileComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public MobileComponentShouldBePresentMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileComponentAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(COMPONENT_SHOULD_BE_PRESENT_METHOD, elementName, componentName)
                : assertInvocation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, elementName, componentName);

        runCheck(invocationName,
                () -> {
                    if (positive) {
                        shouldBePresent(element, componentName);
                    } else {
                        shouldNotBePresent(element, componentName);
                    }
                });
    }

    protected void shouldBePresent(MobileComponentAvailable element, String componentName) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        MobileGetIsPresentOperationType getIsPresent = MobileGetIsPresentOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, getIsPresent)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof ElementNotPresent) {
                // конвертируем эксепшн в ассерт эррор
                throw ElementNotPresent.assertionError(COMPONENT_NOT_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(MobileComponentAvailable element, String componentName) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        MobileGetIsPresentOperationType getIsPresentFunction = MobileGetIsPresentOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, getIsPresentFunction)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            boolean present = successOperationResult.getResult();
            if (present) {
                // конвертируем эксепшн в ассерт эррор
                throw ElementIsPresent.assertionError(COMPONENT_IS_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
        });
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (!(exception instanceof ElementNotPresent)) {
                throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
    }

}
