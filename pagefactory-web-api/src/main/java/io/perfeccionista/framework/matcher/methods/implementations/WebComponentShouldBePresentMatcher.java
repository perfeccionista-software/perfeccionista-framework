package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsPresent;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsPresentOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;

public class WebComponentShouldBePresentMatcher implements WebComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public WebComponentShouldBePresentMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        InvocationInfo invocationName = positive
                ? assertInvocation(COMPONENT_SHOULD_BE_PRESENT_METHOD, element)
                : assertInvocation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, element);

        runCheck(invocationName,
                () -> {
                    if (positive) {
                        shouldBePresent(element, componentName);
                    } else {
                        shouldNotBePresent(element, componentName);
                    }
                });
    }

    protected void shouldBePresent(WebChildElement element, String componentName) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotPresent.assertionError(COMPONENT_NOT_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(WebChildElement element, String componentName) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            boolean present = successOperationResult.getResult();
            if (present) {
                throw ElementIsPresent.assertionError(COMPONENT_IS_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(HtmlAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
        });
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (!(exception instanceof JsElementSearch)) {
                throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
    }

}
