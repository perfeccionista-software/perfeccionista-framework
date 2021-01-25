package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.THEAD_ROW;

public class WebTableCellElementLabelValueExtractor implements WebTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final WebGetLabelAvailable elementFrame;
    private final String elementPath;

    public WebTableCellElementLabelValueExtractor(@NotNull String columnName, @NotNull String elementPath) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebTableCellElementLabelValueExtractor(@NotNull String columnName, @NotNull WebGetLabelAvailable elementFrame) {
        this.columnName = columnName;
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    @Override
    public Map<Integer, String> extractValues( @NotNull WebTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebTable element = filter.getElement();

        // Цепочка от корня страницы до WebListBlock

        WebLocatorHolder tableRowLocator = element.getRequiredLocator(TBODY_ROW);
        WebLocatorHolder tableCellLocator = element.getWebTableFrame()
                .getRequiredBodyLocator(columnName);
        WebBlock tableCellBlock = element.getWebTableFrame()
                .getRequiredBodyMappedBlock(columnName);

        if (TableSection.HEADER == section) {
            tableRowLocator = element.getRequiredLocator(THEAD_ROW);
            tableCellLocator = element.getWebTableFrame()
                    .getRequiredHeaderLocator(columnName);
            tableCellBlock = element.getWebTableFrame()
                    .getRequiredHeaderMappedBlock(columnName);
        }
        if (TableSection.FOOTER == section) {
            tableRowLocator = element.getRequiredLocator(TFOOT_ROW);
            tableCellLocator = element.getWebTableFrame()
                    .getRequiredFooterLocator(columnName);
            tableCellBlock = element.getWebTableFrame()
                    .getRequiredFooterMappedBlock(columnName);
        }

        WebLocatorChain tableLocatorChain = element.getLocatorChain()
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

        // Находим необходимый элемент, заданный по пути или по методу
        WebGetLabelAvailable elementToExtractValue;
        if (elementPath != null) {
            elementToExtractValue = tableCellBlock.getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetLabelAvailable.class);
        } else {
            elementToExtractValue = tableCellBlock.getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetLabelAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(elementToExtractValue);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToExtractValue, operationType, LABEL)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

    @Override
    public WebTableCellElementLabelValueExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementLabelValueExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
