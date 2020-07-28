package io.perfeccionista.framework.pagefactory.pageobjects.implementations;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.jsfunction.DragAndDrop;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class DragAndDropInteraction implements WebElementInteractionImplementation {

    @Override
    public WebChildElement execute(WebChildElement sourceElement, WebChildElement targetElement, Object... args) {
        DragAndDrop dragAndDropFunction = new DragAndDrop(targetElement.getLocation(ROOT).getCenter());
        JsOperation<Void> dragAndDropOperation = JsOperation.of(sourceElement.getLocatorChainTo(ROOT), dragAndDropFunction);
        JsOperationResult<Void> operationResult = sourceElement.getWebBrowserDispatcher().executor()
                .executeOperation(dragAndDropOperation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntryToTop(JsonAttachmentEntry.of("Element", sourceElement.toJson()));
        });
        return sourceElement;
    }

}
