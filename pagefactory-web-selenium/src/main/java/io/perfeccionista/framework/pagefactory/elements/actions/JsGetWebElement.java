package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class JsGetWebElement implements WebElementActionImplementation<WebElement> {

    @Override
    public WebElement execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = new GetWebElement();
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(ROOT), getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

}
