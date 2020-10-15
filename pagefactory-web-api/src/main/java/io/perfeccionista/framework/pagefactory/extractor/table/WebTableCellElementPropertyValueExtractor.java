package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

public class WebTableCellElementPropertyValueExtractor implements WebTableCellValueExtractor<String> {

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
        WebFilterResult filterResult = filter.getFilterResult();
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
        JsOperation<String> jsOperation = elementToExtractValue
                .getProperty(propertyName)
                .orElseThrow(() -> WebElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(elementToExtractValue)))
                .getJsOperation(elementToExtractValue);
        jsOperation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
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

