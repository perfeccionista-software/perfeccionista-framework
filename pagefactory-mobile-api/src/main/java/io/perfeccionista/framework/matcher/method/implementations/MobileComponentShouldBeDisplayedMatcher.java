package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.ElementIsDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;

public class MobileComponentShouldBeDisplayedMatcher implements MobileComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public MobileComponentShouldBeDisplayedMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileComponentAvailable element) {
        var elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, elementName, componentName)
                : assertInvocation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, elementName, componentName);

        runCheck(invocationName,
                () -> {
                    if (positive) {
                        shouldBeDisplayed(element, componentName);
                    } else {
                        shouldNotBeDisplayed(element, componentName);
                    }
                });
    }

    protected void shouldBeDisplayed(MobileComponentAvailable element, String componentName) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        MobileGetIsDisplayedOperationType displayedOperationType = MobileGetIsDisplayedOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, displayedOperationType)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof ElementNotPresent) {
                throw ElementNotDisplayed.assertionError(COMPONENT_NOT_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()), exception)
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean displayed = operationResult.getResult();
        if (!displayed) {
            throw ElementNotDisplayed.assertionError(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
        }
    }

    protected void shouldNotBeDisplayed(MobileComponentAvailable element, String componentName) {
        MobileLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        MobileGetIsDisplayedOperationType displayedOperationType = MobileGetIsDisplayedOperationType.of(element);
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(locatorChainToElement, displayedOperationType)
                .withPageSource();
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor().executeMobileElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = successOperationResult.getResult();
            if (displayed) {
                throw ElementIsDisplayed.assertionError(COMPONENT_IS_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(HtmlAttachmentEntry.of("PageSource", operationResult.getPageSource()));
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
