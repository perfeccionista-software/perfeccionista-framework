package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.HtmlAttachmentEntry;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsPresentException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.exceptions.js.ElementSearchJsException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IS_PRESENT;

public class JsAssertShouldNotBePresent implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, getIsPresentFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifSuccess(operationMultipleResult -> {
            boolean present = operationMultipleResult.singleResult().get();
            if (present) {
                throw new ElementIsPresentException(ELEMENT_IS_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
                        .setType(ExceptionType.ASSERT)
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addAttachmentEntry(HtmlAttachmentEntry.of("OuterHtml", operationResult.getOuterHtml()));
            }
        });
        operationResult.ifException(exception -> {
            if (!ElementSearchJsException.class.equals(exception.getClass())) {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
        // Сюда мы попадаем если упали по ElementSearchJsException - для текущей проверки это нормальная ситуация
        return null;
    }

    @Override
    public Optional<JsOperation<Void>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }
}
