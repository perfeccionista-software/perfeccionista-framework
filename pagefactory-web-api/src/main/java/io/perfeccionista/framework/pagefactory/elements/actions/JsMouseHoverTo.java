package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.jsfunction.MouseHoverTo;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;


/**
 * Not implemented yet
 */
@Deprecated
// TODO: Добавить в функции явный вызов scrollTo, чтобы не приходилось их постоянно дописывать в тесте
public class JsMouseHoverTo implements WebElementJsOperationActionImplementation<Void> {

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
        MouseHoverTo hoverToFunction = new MouseHoverTo();
        WebLocatorChain locatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.addInvokedOnCallFunction(new CheckIsDisplayed()))
                .updateLastLocator(locator -> locator.addInvokedOnCallFunction(new ScrollTo()));
        return JsOperation.of(locatorChain, hoverToFunction);
    }

}
