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
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;

public class WebRadioButtonEnabledMarkExtractor implements WebRadioButtonValueExtractor<Boolean> {

    @Override
    public Map<Integer, Boolean> extractValues(@NotNull WebRadioGroupFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        String hash = filterResult.getHash();
        WebRadioGroup element = filter.getElement();
        WebRadioButton webRadioButton = element.getBlockFrame().getMappedBlockFrame().radioButton();

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(ITEM));

        // Добавляем в цепочку локаторов операции локаторы до блока RadioButtonBlock
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(webRadioButton);
        WebElementOperation<Boolean> operation = WebElementOperationHandler.of(webRadioButton, operationType, ENABLED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(radioGroupLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
