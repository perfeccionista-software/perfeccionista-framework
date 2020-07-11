package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetColor;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.plugin.Color;
import org.jetbrains.annotations.NotNull;

public class JsGetColor implements WebElementActionImplementation<Color> {

    @Override
    public @NotNull Color execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        String cssProperty = (String) args[1];
        GetColor getColorFunction = new GetColor(cssProperty);
        JsOperation<Color> operation = JsOperation.of(element.getLocatorChainTo(component), getColorFunction);
        JsOperationResult<Color> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}

