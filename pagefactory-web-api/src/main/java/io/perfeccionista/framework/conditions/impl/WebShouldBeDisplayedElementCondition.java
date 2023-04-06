package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementOperationAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;

/**
 * TODO: Должен быть не стейтфул
 */
public class WebShouldBeDisplayedElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBeDisplayedElementCondition() {
        this.componentName = DISPLAYED;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        if (positive) {
            checkDisplayed(element, invocationInfo);
        } else {
            checkNotDisplayed(element, invocationInfo);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_DISPLAYED_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_DISPLAYED_METHOD, elementName);
    }

    @Override
    public WebShouldBeDisplayedElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebIsDisplayedAvailable interface
        return this;
    }

    @Override
    public WebShouldBeDisplayedElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBeDisplayedElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void checkDisplayed(WebChildElement element, InvocationInfo invocationInfo) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element, componentName);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotDisplayed.assertionError(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                                .setProcessed(true)
                                .addSuppressedException(exception)
                                .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                                .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean displayed = operationResult.getResult();
        if (!displayed) {
            throw ElementNotDisplayed.assertionError(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
        }
    }

    protected void checkNotDisplayed(WebChildElement element, InvocationInfo invocationInfo) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element, componentName);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeWebElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = successOperationResult.getResult();
            if (displayed) {
                throw ElementIsDisplayed.assertionError(ELEMENT_IS_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
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
