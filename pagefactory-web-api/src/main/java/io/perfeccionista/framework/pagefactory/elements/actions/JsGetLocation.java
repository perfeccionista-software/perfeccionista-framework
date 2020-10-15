package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetLocation;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

public class JsGetLocation implements WebElementJsOperationActionImplementation<Location> {

    @Override
    public @NotNull Location execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Location> operation = getJsOperation(element, args);
        JsOperationResult<Location> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<Location> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        String componentName = (String) args[0];
        return JsOperation.of(element.getLocatorChainTo(componentName), new GetLocation());
    }

}