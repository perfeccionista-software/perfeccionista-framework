package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

public class JsGetScreenshot implements WebElementActionImplementation<Screenshot> {

    @Override
    public Screenshot execute(WebChildElement element, Object... args) {
        GetScreenshot getScreenshotFunction = new GetScreenshot();
        JsOperation<Screenshot> operation = JsOperation.of(element.getLocatorChain(), getScreenshotFunction);
        JsOperationResult<Screenshot> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
