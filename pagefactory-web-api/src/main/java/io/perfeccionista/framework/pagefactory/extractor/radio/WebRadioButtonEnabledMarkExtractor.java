package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;

public class WebRadioButtonEnabledMarkExtractor implements WebRadioButtonValueExtractor<Boolean> {

    @Override
    public Map<Integer, Boolean> extractValues(@NotNull WebRadioGroupFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        String hash = filterResult.getHash();
        WebRadioGroup element = filter.getElement();
        WebRadioButton webRadioButton = element.getWebRadioGroupFrame().getMappedBlockFrame().radioButton();

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(RADIO));

        // Добавляем в цепочку локаторов операции локаторы до блока RadioButtonBlock
        JsOperation<Boolean> jsOperation = webRadioButton
                .getJsOperationActionImplementation(IS_ENABLED_METHOD, Boolean.class)
                .getJsOperation(webRadioButton);
        jsOperation.getLocatorChain()
                .addFirstLocators(radioGroupLocatorChain);

        // Выполняем операцию
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
