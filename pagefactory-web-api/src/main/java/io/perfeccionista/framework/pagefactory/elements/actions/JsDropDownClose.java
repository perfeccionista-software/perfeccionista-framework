package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.jsfunction.MouseClickLeftButton;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLOSE;

@Deprecated
public class JsDropDownClose implements WebElementJsOperationActionImplementation<Void> {

    @Override
    public Void execute(WebChildElementBase element, Object... args) {
        WebDropDownList dropDownList = (WebDropDownList) element;
        if (dropDownList.isOpen()) {
            JsOperation<Void> operation = getJsOperation(element, args);
            JsOperationResult<Void> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
            operationResult.ifException(exception -> {
                throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            });
        }
        return null;
    }

    @Override
    public JsOperation<Void> getJsOperation(WebChildElementBase element, Object... args) {
        MouseClickLeftButton clickFunction = new MouseClickLeftButton();
        WebLocatorChain locatorChain = element.getLocatorChainTo(CLOSE);
        locatorChain.getLastLocator()
                .addInvokedOnCallFunction(new CheckIsDisplayed())
                .addInvokedOnCallFunction(new ScrollTo());
        return JsOperation.of(locatorChain, clickFunction);
    }

}
