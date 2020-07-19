package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class JsGetScreenshot implements WebElementActionImplementation<Screenshot> {

    @Override
    public @NotNull Screenshot execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        GetScreenshot getScreenshotFunction = new GetScreenshot();
        JsOperation<Screenshot> operation = JsOperation.of(element.getLocatorChainTo(component), getScreenshotFunction);
        JsOperationResult<Screenshot> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

    @Override
    public Optional<JsOperation<Screenshot>> getJsOperation(WebChildElement element, Object... args) {
        String component = (String) args[0];
        GetScreenshot getScreenshotFunction = new GetScreenshot();
        return Optional.of(JsOperation.of(element.getLocatorChainTo(component), getScreenshotFunction));
    }

}
