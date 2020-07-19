package io.perfeccionista.framework.pagefactory.elements.asserts;

import io.perfeccionista.framework.attachment.HtmlAttachmentEntry;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.exceptions.js.ElementSearchJsException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IS_DISPLAYED;

public class JsAssertShouldNotBeDisplayed implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        String component = (String) args[0];
        WebLocatorChain locatorChainToElement = (null == component) ? element.getLocatorChain() : element.getLocatorChainTo(component);
        GetIsDisplayed isDisplayedFunction = new GetIsDisplayed();
        JsOperation<Boolean> operation = JsOperation.of(locatorChainToElement, isDisplayedFunction)
                .withOuterHtml();
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifSuccess(operationMultipleResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = operationResult.singleResult().get();
            if (displayed) {
                throw new ElementIsDisplayedException(ELEMENT_IS_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
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
