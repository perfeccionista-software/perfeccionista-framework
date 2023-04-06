package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementIsPresent;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementOperationAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsPresentOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;

public class WebShouldBePresentElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBePresentElementCondition() {
        this.componentName = PRESENTED;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        if (positive) {
            shouldBePresent(element, invocationInfo);
        } else {
            shouldNotBePresent(element, invocationInfo);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_PRESENT_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_PRESENT_METHOD, elementName);
    }

    @Override
    public WebShouldBePresentElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebIsPresentAvailable interface
        return this;
    }

    @Override
    public WebShouldBePresentElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBePresentElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldBePresent(WebIsPresentAvailable element, InvocationInfo invocationInfo) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element, componentName);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotPresent.assertionError(ELEMENT_NOT_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(WebIsPresentAvailable element, InvocationInfo invocationInfo) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element, componentName);
        WebElementOperation<Boolean> operation = WebElementIsPresentOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
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
