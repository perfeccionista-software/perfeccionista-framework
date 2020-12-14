package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.ref.SourceParameterRef;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;

import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.beSorted;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textCellValue;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class TableCheckSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("table {webElement} has {integerValue} row(s)")
    @Given("таблица {webElement} содержит {integerValue} строк(а|и|у)")
    public void tableHasSize(WebElementParameter<WebTable> elementFinder,
                             ValueIntegerParameter integerValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTable.class)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("table {webElement} has {integerValue} row(s) with")
    @Given("таблица {webElement} содержит {integerValue} строк(а|и|у), где")
    public void filteredTableHasSize(WebElementParameter<WebTable> elementFinder,
                                     ValueIntegerParameter integerValue,
                                     WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filter(itemFilter)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("text table {webElement} has {integerValue} row(s)")
    @Given("текстовая таблица {webElement} содержит {integerValue} строк(а|и|у)")
    public void textTableHasSize(WebElementParameter<WebTextTable> elementFinder,
                                 ValueIntegerParameter integerValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTextTable.class)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("text table {webElement} has {integerValue} row(s) with")
    @Given("текстовая таблица {webElement} содержит {integerValue} строк(а|и|у), где")
    public void filteredTextTableHasSize(WebElementParameter<WebTextTable> elementFinder,
                                         ValueIntegerParameter integerValue,
                                         WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filter(itemFilter)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param webTableColumn -
     * @param valueExtractor -
     * @param blockElementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in table {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection}")
    @Given("в таблице {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection}")
    public void tableSorted(WebElementParameter<WebTable> elementFinder,
                            @SourceParameterRef("elementFinder") WebTableColumnParameter webTableColumn,
                            WebTableValueExtractorParameter valueExtractor,
                            @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                            StringComparatorTypeParameter comparatorType,
                            SortDirectionParameter sortDirection) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .extractRows(valueExtractor.createExtractorFor(webTableColumn.getRaw(), blockElementFinder.getRaw()))
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    /**
     *
     * @param elementFinder -
     * @param webTableColumn -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in text table {webElement} in column {webTableColumn} values in {comparatorType} format sorted {sortDirection}")
    @Given("в текстовой таблице {webElement} в столбце {webTableColumn} значения в формате {comparatorType} отсортированы {sortDirection}")
    public void textTableSorted(WebElementParameter<WebTextTable> elementFinder,
                                @SourceParameterRef("elementFinder") WebTableColumnParameter webTableColumn,
                                StringComparatorTypeParameter comparatorType,
                                SortDirectionParameter sortDirection) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .extractRows(textCellValue(webTableColumn.getRaw()))
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    /**
     *
     * @param elementFinder -
     * @param valueExtractor -
     * @param blockElementFinder -
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Given("in table {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection} in rows with")
    @Given("в таблице {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection} в строках, где")
    public void filteredTableSorted(WebElementParameter<WebTable> elementFinder,
                                    @SourceParameterRef("elementFinder") WebTableColumnParameter webTableColumn,
                                    WebTableValueExtractorParameter valueExtractor,
                                    @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                                    StringComparatorTypeParameter comparatorType,
                                    SortDirectionParameter sortDirection,
                                    WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filter(itemFilter)
                                .extractRows(valueExtractor.createExtractorFor(webTableColumn.getRaw(), blockElementFinder.getRaw()))
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Given("in text table {webElement} in column {webTableColumn} values in {comparatorType} format sorted {sortDirection} in rows with")
    @Given("в текстовой таблице {webElement} в столбце {webTableColumn} значения в формате {comparatorType} отсортированы {sortDirection} в строках, где")
    public void filteredTextTableSorted(WebElementParameter<WebTextTable> elementFinder,
                                        @SourceParameterRef("elementFinder") WebTableColumnParameter webTableColumn,
                                        StringComparatorTypeParameter comparatorType,
                                        SortDirectionParameter sortDirection,
                                        WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filter(itemFilter)
                                .extractRows(webTableColumn.getRaw())
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

}
