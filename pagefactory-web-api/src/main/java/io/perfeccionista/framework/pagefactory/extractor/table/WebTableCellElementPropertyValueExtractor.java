package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetStringAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.THEAD_ROW;

public class WebTableCellElementPropertyValueExtractor implements WebTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final WebChildElement elementFrame;
    private final String elementPath;
    private final String propertyName;

    public WebTableCellElementPropertyValueExtractor(@NotNull String columnName,
                                                     @NotNull String elementPath,
                                                     @NotNull String propertyName) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.propertyName = propertyName;
    }

    public WebTableCellElementPropertyValueExtractor(@NotNull String columnName,
                                                     @NotNull WebChildElement elementFrame,
                                                     @NotNull String propertyName) {
        this.columnName = columnName;
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.propertyName = propertyName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTableFilter filter) {
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
        WebChildElement elementToExtractValue;
        if (elementPath != null) {
            elementToExtractValue = tableCellBlock.getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToExtractValue = tableCellBlock.getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        Optional<WebElementPropertyHolder> optionalPropertyHolder = elementToExtractValue.getProperty(propertyName);
        WebElementOperation<String> operation;
        if (optionalPropertyHolder.isPresent()) {
            WebElementPropertyHolder propertyHolder = optionalPropertyHolder.get();
            operation = propertyHolder.getOperation(elementToExtractValue);
        } else {
            WebGetStringAttributeValueOperationType operationType = WebGetStringAttributeValueOperationType.of(elementToExtractValue, propertyName);
            operation = WebElementOperationHandler.of(elementToExtractValue, operationType, propertyName).getOperation();
        }
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
    public WebTableCellElementPropertyValueExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementPropertyValueExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}

