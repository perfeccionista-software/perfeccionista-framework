package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetColor;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.plugin.Color;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR;

public class JsAssertShouldNotHaveColor implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        String cssProperty = (String) args[1];
        Color expectedColor = (Color) args[2];

        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetColor getColorFunction = new GetColor(cssProperty);
        JsOperation<Color> operation = JsOperation.of(locatorChainToElement, getColorFunction);
        JsOperationResult<Color> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        Color actualColor = operationResult.singleResult().get();
        if (expectedColor.equals(actualColor)) {
            throw new ElementColorException(ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR.getMessage(component))
                    .setType(ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected color", expectedColor.toString()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual color", actualColor.toString()));
        }
        return null;
    }

}
