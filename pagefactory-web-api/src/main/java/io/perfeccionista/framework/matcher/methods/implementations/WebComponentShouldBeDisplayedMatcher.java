package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;

public class WebComponentShouldBeDisplayedMatcher implements WebComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public WebComponentShouldBeDisplayedMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        InvocationInfo invocationName = positive
                ? assertInvocation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, element)
                : assertInvocation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, element);

        runCheck(invocationName,
                () -> {
                    if (positive) {
                        shouldBeDisplayed(element, componentName);
                    } else {
                        shouldNotBeDisplayed(element, componentName);
                    }
                });
    }

    protected void shouldBeDisplayed(WebChildElement element, String componentName) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotDisplayed.assertionError(COMPONENT_NOT_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.empty()))
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

    protected void shouldNotBeDisplayed(WebChildElement element, String componentName) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, componentName)
                .getOperation()
                .withPageSource();
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = successOperationResult.getResult();
            if (displayed) {
                throw ElementIsDisplayed.assertionError(COMPONENT_IS_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
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
