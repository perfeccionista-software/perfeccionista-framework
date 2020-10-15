package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsSelected;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.SELECTED;

public class JsGetIsSelected implements WebElementJsOperationActionImplementation<Boolean> {

    @Override
    public @NotNull Boolean execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Boolean> operation = getJsOperation(element, args);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<Boolean> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        return JsOperation.of(element.getLocatorChainTo(SELECTED), new GetIsSelected());
    }

}
