package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileText;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilter;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.THEAD_ROW;

public class MobileTextTableCellTextValueExtractor implements MobileTextTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;

    public MobileTextTableCellTextValueExtractor(@NotNull String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull MobileTextTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        MobileTextTable element = filter.getElement();

        // Цепочка от корня страницы до MobileListBlock
        MobileLocatorHolder tableRowLocator = element.getRequiredLocator(TBODY_ROW);
        MobileLocatorHolder tableCellLocator = element.getMobileTextTableFrame()
                .getRequiredBodyLocator(columnName);
        DefaultMobileTextBlock tableCellBlock = element.getMobileTextTableFrame()
                .getRequiredBodyMappedBlock(columnName);

        if (TableSection.HEADER == section) {
            tableRowLocator = element.getRequiredLocator(THEAD_ROW);
            tableCellLocator = element.getMobileTextTableFrame()
                    .getRequiredHeaderLocator(columnName);
            tableCellBlock = element.getMobileTextTableFrame()
                    .getRequiredHeaderMappedBlock(columnName);
        }
        if (TableSection.FOOTER == section) {
            tableRowLocator = element.getRequiredLocator(TFOOT_ROW);
            tableCellLocator = element.getMobileTextTableFrame()
                    .getRequiredFooterLocator(columnName);
            tableCellBlock = element.getMobileTextTableFrame()
                    .getRequiredFooterMappedBlock(columnName);
        }

        MobileLocatorChain tableLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash));

        if (TableSection.BODY == section) {
            tableLocatorChain.addLastLocator(tableRowLocator)
                    .updateLastLocator(locator -> locator.setIndexes(indexes))
                    .addLastLocator(tableCellLocator);
        } else {
            tableLocatorChain.addLastLocator(tableRowLocator)
                    .addLastLocator(tableCellLocator);
        }

        MobileText elementToExtractValue = tableCellBlock.textLink();

        // Добавляем в цепочку локаторов операции локаторы до блока MobileListBlock

        MobileElementOperation<String> operation = MobileElementOperation.of(tableLocatorChain, MobileGetTextOperationType.of(elementToExtractValue));

        // Выполняем операцию
        MobileElementOperationResult<String> operationResult = element.getMobileDeviceDispatcher().executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

    @Override
    public MobileTextTableCellTextValueExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public MobileTextTableCellTextValueExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
