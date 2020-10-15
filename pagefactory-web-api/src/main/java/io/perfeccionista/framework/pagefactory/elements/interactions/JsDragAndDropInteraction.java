package io.perfeccionista.framework.pagefactory.elements.interactions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.jsfunction.DragAndDrop;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class JsDragAndDropInteraction implements WebElementInteractionImplementation {

    @Override
    public WebChildElement execute(@NotNull WebChildElement sourceElement, @NotNull WebChildElement targetElement, Object... args) {
        //noinspection ConstantConditions
        DragAndDrop dragAndDropFunction = new DragAndDrop(targetElement.getLocation(ROOT).getCenter());
        JsOperation<Void> dragAndDropOperation = JsOperation.of(sourceElement.getLocatorChainTo(ROOT), dragAndDropFunction);
        JsOperationResult<Void> operationResult = sourceElement.getWebBrowserDispatcher().executor()
                .executeOperation(dragAndDropOperation);
        operationResult.ifException(exception -> {
            throw exception.addFirstAttachmentEntry(JsonAttachmentEntry.of("Element", sourceElement.toJson()));
        });
        return sourceElement;
    }

}
