package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

public class JsGetScreenshot implements WebElementJsOperationActionImplementation<Screenshot> {

    @Override
    public @NotNull Screenshot execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Screenshot> operation = getJsOperation(element, args);
        JsOperationResult<Screenshot> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<Screenshot> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        String componentName = (String) args[0];
        return JsOperation.of(element.getLocatorChainTo(componentName), new GetScreenshot());
    }

}
