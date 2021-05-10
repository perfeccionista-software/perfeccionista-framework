package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebTextListBlockTextValueExtractor implements WebTextListBlockValueExtractor<String> {

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTextListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebTextList element = filter.getElement();
        WebLink elementToExtractValue = element.getWebTextListFrame().getMappedBlockFrame().textLink();

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(LI))
                .updateLastLocator(locator -> locator.setIndexes(indexes));

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToExtractValue);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToExtractValue, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

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
