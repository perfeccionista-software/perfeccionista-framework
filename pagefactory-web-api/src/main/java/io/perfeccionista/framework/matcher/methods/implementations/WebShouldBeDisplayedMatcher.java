package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_DISPLAYED_METHOD;

public class WebShouldBeDisplayedMatcher implements WebIsDisplayedAvailableMatcher {

    private final boolean positive;

    public WebShouldBeDisplayedMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_BE_DISPLAYED_METHOD, element)
                : assertInvocation(SHOULD_NOT_BE_DISPLAYED_METHOD, element);

        runCheck(invocationName,
                () -> {
                    if (positive) {
                        shouldBeDisplayed(element);
                    } else {
                        shouldNotBeDisplayed(element);
                    }
                });
    }

    protected void shouldBeDisplayed(WebChildElement element) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, DISPLAYED)
                .getOperation()
                .withPageSource();
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

    protected void shouldNotBeDisplayed(WebChildElement element) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(element, operationType, DISPLAYED)
                .getOperation()
                .withPageSource();
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
