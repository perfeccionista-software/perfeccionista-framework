package io.perfeccionista.framework.pagefactory.pageobjects.extractors;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

public class HrefAttributeWebElementPropertyExtractor implements WebElementPropertyExtractor<WebChildElement> {

    @Override
    public String extract(WebChildElement element, WebLocatorHolder locatorHolder) {
        JsOperation<String> operation = JsOperation.of(element.getLocatorChain()
                .addLocator(locatorHolder), new GetAttribute("href"));
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
