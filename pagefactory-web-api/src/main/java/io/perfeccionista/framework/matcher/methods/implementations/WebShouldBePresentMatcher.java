package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsPresent;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsPresentOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_PRESENT_METHOD;

public class WebShouldBePresentMatcher implements WebIsPresentAvailableMatcher {

    private final boolean positive;

    public WebShouldBePresentMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebIsPresentAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_BE_PRESENT_METHOD, element)
                : assertInvocation(SHOULD_NOT_BE_PRESENT_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    if (positive) {
                        shouldBePresent(element);
                    } else {
                        shouldNotBePresent(element);
                    }
                });
    }

    protected void shouldBePresent(WebIsPresentAvailable element) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, PRESENTED)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotPresent.assertionError(ELEMENT_NOT_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.of()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(WebIsPresentAvailable element) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, PRESENTED)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            boolean present = successOperationResult.getResult();
            if (present) {
                throw ElementIsPresent.assertionError(ELEMENT_IS_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
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
