package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetText;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;

public class JsGetLabel implements WebElementActionImplementation<String> {

    @Override
    public @Nullable String execute(WebChildElement element, Object... args) {
        GetText getTextFunction = new GetText();
        JsOperation<String> operation = JsOperation.of(element.getLocatorChainTo(LABEL), getTextFunction);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
