package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

public class WebTextTableCellTextValueExtractor implements WebTextTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;

    public WebTextTableCellTextValueExtractor(@NotNull String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTextTableFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebTextTable element = filter.getElement();

        // Цепочка от корня страницы до WebListBlock
        WebLocatorHolder tableRowLocator = element.getRequiredLocator(TBODY_ROW);
        WebLocatorHolder tableCellLocator = element.getWebTextTableFrame()
                .getRequiredBodyLocator(columnName);
        DefaultWebTextBlock tableCellBlock = element.getWebTextTableFrame()
                .getRequiredBodyMappedBlock(columnName);

        if (TableSection.HEADER == section) {
            tableRowLocator = element.getRequiredLocator(THEAD_ROW);
            tableCellLocator = element.getWebTextTableFrame()
                    .getRequiredHeaderLocator(columnName);
            tableCellBlock = element.getWebTextTableFrame()
                    .getRequiredHeaderMappedBlock(columnName);
        }
        if (TableSection.FOOTER == section) {
            tableRowLocator = element.getRequiredLocator(TFOOT_ROW);
            tableCellLocator = element.getWebTextTableFrame()
                    .getRequiredFooterLocator(columnName);
            tableCellBlock = element.getWebTextTableFrame()
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

        WebLink elementToExtractValue = tableCellBlock.textLink();

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        JsOperation<String> jsOperation = elementToExtractValue
                .getJsOperationActionImplementation(GET_TEXT_METHOD, String.class)
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
    public WebTextTableCellTextValueExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTextTableCellTextValueExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
