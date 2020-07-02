package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsDisabledException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;

public class SeleniumAssertShouldBeEnabled implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(ENABLED), getWebElementFunction);
        WebElement webElement = element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
        boolean enabled = element.getWebBrowserDispatcher().getExceptionMapper().map(webElement::isEnabled);
        if (enabled) {
            return null;
        }
        throw new ElementIsDisabledException(ELEMENT_IS_DISABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                .setType(ExceptionType.ASSERT)
                .setProcessed(true)
                .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
    }

}
