package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementDoesNotHaveExpectedState;
import io.perfeccionista.framework.exceptions.ElementHasUnexpectedState;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementOperationAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_HAS_UNEXPECTED_STATE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_STATE_NOT_FOUND;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_STATE_METHOD;

public class WebShouldHaveStateElementCondition implements WebElementCondition {

    private final String stateName;
    private boolean positive;

    public WebShouldHaveStateElementCondition(@NotNull String stateName) {
        this.stateName = stateName;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        WebElementStateHolder stateHolder = element.getStateHolder(stateName)
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_STATE_NOT_FOUND.getMessage(stateName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
        if (positive) {
            shouldHaveState(element, stateHolder, invocationInfo);
        } else {
            shouldNotHaveState(element, stateHolder, invocationInfo);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_STATE_METHOD, elementName, stateName)
                : assertInvocation(SHOULD_NOT_HAVE_STATE_METHOD, elementName, stateName);
    }

    @Override
    public WebShouldHaveStateElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebGetColorAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveStateElementCondition forComponent(@NotNull String componentName) {
        // unused
        return this;
    }

    @Override
    public WebShouldHaveStateElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHaveState(WebChildElement element, WebElementStateHolder stateHolder, InvocationInfo invocationInfo) {
        WebElementOperation<Boolean> operation = stateHolder.getOperation(element)
                .withPageSource();
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher()
                .executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException);
                });
        if (operationResult.hasResult()) {
            boolean result = operationResult.getNotNullResult();
            if (!result) {
                throw ElementDoesNotHaveExpectedState.assertionError(ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE.getMessage(element.getElementIdentifier().getLastUsedName(), stateName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            return;
        }
        throw ElementDoesNotHaveExpectedState.assertionError(ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE.getMessage(element.getElementIdentifier().getLastUsedName(), stateName))
                .setProcessed(true)
                .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
    }

    protected void shouldNotHaveState(WebChildElement element, WebElementStateHolder stateHolder, InvocationInfo invocationInfo) {
        WebElementOperation<Boolean> operation = stateHolder.getOperation(element);
        invocationInfo.setMainAttachmentEntry(WebElementOperationAttachmentEntry.of(operation));
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher()
                .executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException);
                });
        if (operationResult.hasResult()) {
            boolean result = operationResult.getNotNullResult();
            if (result) {
                throw ElementHasUnexpectedState.assertionError(ELEMENT_HAS_UNEXPECTED_STATE.getMessage(element.getElementIdentifier().getLastUsedName(), stateName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("PageSource", operationResult.getPageSource()));
            }
            // проверка результата отрицательная - стейт не присутствует
        }
        // Не нашли элемент стейта - значит стейт не присутствует
    }

}
