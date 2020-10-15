package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.jsfunction.SendKeysAsText;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;

/**
 * Not implemented yet
 */
@Deprecated
public class JsSendKeysAsText implements WebElementJsOperationActionImplementation<Void> {

    @Override
    public Void execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Void> operation = getJsOperation(element, args);
        JsOperationResult<Void> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<Void> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        String valueToInput = (String) args[0];
        WebLocatorChain locatorChain = element.getLocatorChainTo(INPUT)
                .updateLastLocator(locator -> locator.addInvokedOnCallFunction(new CheckIsDisplayed()))
                .updateLastLocator(locator -> locator.addInvokedOnCallFunction(new ScrollTo()));
        return JsOperation.of(locatorChain, new SendKeysAsText(valueToInput));
    }

}


