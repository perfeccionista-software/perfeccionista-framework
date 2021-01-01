package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed;
import io.perfeccionista.framework.exceptions.WebElementNotDisplayed;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COMPONENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COMPONENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;

public class WebComponentIsDisplayedMatcher implements WebComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public WebComponentIsDisplayedMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebComponentAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, element)
                : assertInvocation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    if (positive) {
                        shouldBeDisplayed(element, componentName);
                    } else {
                        shouldNotBeDisplayed(element, componentName);
                    }
                });
    }

    protected void shouldBeDisplayed(WebComponentAvailable element, String componentName) {
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            if (exception instanceof JsElementSearch) {
                throw WebElementNotDisplayed.assertionError(COMPONENT_NOT_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.of()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("OuterHtml", operationResult.getOuterHtml()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean displayed = operationResult.getResult();
        if (!displayed) {
            throw WebElementNotDisplayed.assertionError(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("OuterHtml", operationResult.getOuterHtml()));
        }
    }

    protected void shouldNotBeDisplayed(WebComponentAvailable element, String componentName) {
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = successOperationResult.getResult();
            if (displayed) {
                throw WebElementIsDisplayed.assertionError(COMPONENT_IS_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(HtmlAttachmentEntry.of("OuterHtml", operationResult.getOuterHtml()));
            }
        });
        operationResult.ifException(exception -> {
            if (!(exception instanceof JsElementSearch)) {
                throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
    }

}
