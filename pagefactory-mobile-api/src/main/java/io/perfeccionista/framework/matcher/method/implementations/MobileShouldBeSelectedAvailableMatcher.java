package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.ElementIsSelected;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.ElementNotSelected;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.method.MobileIsSelectedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsSelectedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IS_SELECTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_SELECTED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;

public class MobileShouldBeSelectedAvailableMatcher implements MobileIsSelectedAvailableMatcher {

    private final boolean positive;

    public MobileShouldBeSelectedAvailableMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileIsSelectedAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_SELECTED_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_SELECTED_METHOD, elementName);

        repeatInvocation(invocationName,
                () -> check(element, positive));
    }

    protected static void check(MobileIsDisplayedAvailable element, boolean positive) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(SELECTED);
        MobileGetIsSelectedOperationType selectedOperationType = MobileGetIsSelectedOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, selectedOperationType)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof ElementNotPresent) {
                throw ElementNotDisplayed.assertionError(COMPONENT_NOT_DISPLAYED.getMessage(DISPLAYED, element.getElementIdentifier().getLastUsedName()), exception)
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean selected = operationResult.getResult();
        if (selected != positive) {
            PerfeccionistaAssertionError error;
            if (positive) {
                error = ElementNotSelected.assertionError(ELEMENT_NOT_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()));
            } else {
                error = ElementIsSelected.assertionError(ELEMENT_IS_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()));
            }
            throw error
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
        }
    }
}
