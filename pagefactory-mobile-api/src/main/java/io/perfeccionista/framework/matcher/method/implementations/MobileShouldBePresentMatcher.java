package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.ElementIsPresent;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;

public class MobileShouldBePresentMatcher implements MobileIsPresentAvailableMatcher {

    private final boolean positive;

    public MobileShouldBePresentMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileIsPresentAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_PRESENT_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_PRESENT_METHOD, elementName);

        repeatInvocation(invocationName,
                () -> {
                    if (positive) {
                        shouldBePresent(element);
                    } else {
                        shouldNotBePresent(element);
                    }
                });
    }

    protected void shouldBePresent(MobileIsPresentAvailable element) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(PRESENTED);
        MobileGetIsPresentOperationType getIsPresent = MobileGetIsPresentOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, getIsPresent)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof ElementNotPresent) {
                // конвертируем эксепшн в ассерт эррор
                throw ElementNotPresent.assertionError(COMPONENT_NOT_PRESENT.getMessage(PRESENTED, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(MobileIsPresentAvailable element) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(PRESENTED);
        MobileGetIsPresentOperationType getIsPresentFunction = MobileGetIsPresentOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, getIsPresentFunction)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            boolean present = successOperationResult.getResult();
            if (present) {
                // конвертируем эксепшн в ассерт эррор
                throw ElementIsPresent.assertionError(COMPONENT_IS_PRESENT.getMessage(PRESENTED, element.getElementIdentifier().getLastUsedName()))
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
