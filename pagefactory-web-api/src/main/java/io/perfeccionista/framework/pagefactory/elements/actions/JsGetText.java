package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetText;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

public class JsGetText implements WebElementJsOperationActionImplementation<String> {

    @Override
    public @Nullable String execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<String> operation = getJsOperation(element, args);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<String> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        return JsOperation.of(element.getLocatorChainTo(TEXT), new GetText());
    }

}
