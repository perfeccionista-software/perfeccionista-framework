package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

// TODO: Возможно, тут следует сделать проверку CheckIsPresent;
public class JsScrollTo implements WebElementJsOperationActionImplementation<Void> {

    @Override
    public Void execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Void> operation = getJsOperation(element, args);
        JsOperationResult<Void> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return null;
    }

    @Override
    public @NotNull JsOperation<Void> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        return JsOperation.of(element.getLocatorChain(), new ScrollTo());
    }

}
