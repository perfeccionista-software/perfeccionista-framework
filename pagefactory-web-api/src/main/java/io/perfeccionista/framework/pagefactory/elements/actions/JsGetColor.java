package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetColor;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;

public class JsGetColor implements WebElementJsOperationActionImplementation<Color> {

    @Override
    public @NotNull Color execute(@NotNull WebChildElementBase element, Object... args) {
        JsOperation<Color> operation = getJsOperation(element, args);
        JsOperationResult<Color> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        return operationResult.getResult();
    }

    @Override
    public @NotNull JsOperation<Color> getJsOperation(@NotNull WebChildElementBase element, Object... args) {
        String componentName = (String) args[0];
        String cssProperty = (String) args[1];
        return JsOperation.of(element.getLocatorChainTo(componentName), new GetColor(cssProperty));
    }

}

