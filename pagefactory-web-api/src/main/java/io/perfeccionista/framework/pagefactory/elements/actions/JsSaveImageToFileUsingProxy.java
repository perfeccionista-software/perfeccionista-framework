package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.IMAGE;

/**
 * Not implemented yet
 */
@Deprecated
public class JsSaveImageToFileUsingProxy implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElementBase element, Object... args) {
        JsOperation<String> operation = JsOperation.of(element.getLocatorChainTo(IMAGE), new GetAttribute("src"));
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        String imagePath = operationResult.getResult();
        // Get implementation from https://github.com/selenide/selenide/blob/master/src/main/java/com/codeborne/selenide/impl/DownloadFileWithProxyServer.java
        return null;
    }

}
