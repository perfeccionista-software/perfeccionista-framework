package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLOSE;

public class SeleniumClose implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebDropDownList dropDownList = (WebDropDownList) element;
        if (dropDownList.isOpen()) {
            GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
            JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(CLOSE), getWebElementFunction);
            JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
            operationResult.ifException(exception -> {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            });
            WebElement webElement = operationResult.singleResult().get();
            element.getWebBrowserDispatcher().getExceptionMapper()
                    .map(webElement::click, element.getElementIdentifier().getLastUsedName())
                    .ifException(exception -> {
                        throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                    });
        }
        return null;
    }

}
