package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementIsPresent;
import io.perfeccionista.framework.exceptions.WebElementNotPresent;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COMPONENT_IS_PRESENT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COMPONENT_NOT_PRESENT;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;

public class WebComponentIsPresentMatcher implements WebComponentAvailableMatcher {

    private final String componentName;
    private final boolean positive;

    public WebComponentIsPresentMatcher(@NotNull String componentName, boolean positive) {
        this.componentName = componentName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebComponentAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(COMPONENT_SHOULD_BE_PRESENT_METHOD, element)
                : InvocationName.of(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    if (positive) {
                        shouldBePresent(element, componentName);
                    } else {
                        shouldNotBePresent(element, componentName);
                    }
                });
    }

    protected void shouldBePresent(WebComponentAvailable element, String componentName) {
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, getIsPresentFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            if (exception instanceof JsElementSearch) {
                throw WebElementNotPresent.assertionError(COMPONENT_NOT_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.of()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("OuterHtml", operationResult.getOuterHtml()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
    }

    protected void shouldNotBePresent(WebComponentAvailable element, String componentName) {
        WebLocatorChain locatorChainToElement = element.getLocatorChainTo(componentName);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, getIsPresentFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            boolean present = successOperationResult.getResult();
            if (present) {
                throw WebElementIsPresent.assertionError(COMPONENT_IS_PRESENT.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
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
