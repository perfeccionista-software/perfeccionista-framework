package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.RADIO;

public class WebRadioButtonLabelValueExtractor implements WebRadioButtonValueExtractor<String> {

    @Override
    public Map<Integer, String> extractValues(@NotNull WebRadioGroupFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        String hash = filterResult.getHash();
        WebRadioGroup element = filter.getElement();
        WebRadioButton webRadioButton = element.getWebRadioGroupFrame().getMappedBlockFrame().radioButton();

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(RADIO));

        // Добавляем в цепочку локаторов операции локаторы до блока RadioButtonBlock
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(webRadioButton);
        WebElementOperation<String> operation = WebElementOperationHandler.of(webRadioButton, operationType, LABEL)
                .getOperation()
                .updateLocatorChain(locatorChain -> locatorChain.addFirstLocators(radioGroupLocatorChain));

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
