package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileText;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilter;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;

public class MobileTextListBlockTextValueExtractor implements MobileTextListBlockValueExtractor<String> {

    @Override
    public Map<Integer, String> extractValues(@NotNull MobileTextListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        MobileTextList element = filter.getElement();
        MobileText elementToFilter = element.getMobileTextListFrame().getMappedBlockFrame().textLink();

        // Цепочка от корня страницы до MobileListBlock
        MobileLocatorChain listLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(LI))
                .updateLastLocator(locator -> locator.setIndexes(indexes));

        // Добавляем в цепочку локаторов операции локаторы до блока MobileListBlock
        MobileElementOperation<String> operation = MobileElementOperation.of(listLocatorChain, MobileGetTextOperationType.of(elementToFilter));

        // Выполняем операцию
        MobileElementOperationResult<String> operationResult = element.getMobileDeviceDispatcher().executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                });
        return operationResult.getResults();
    }

}

