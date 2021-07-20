package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.reference.SourceParameterReference;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;

import static io.perfeccionista.framework.Web.*;


// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebTableCheckStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("таблица {webElement} содержит {integerValue} строк(а|и|у)")
    @Given("table {webElement} has {integerValue} row(s)")
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
    @Дано("таблица {webElement} содержит {integerValue} строк(а|и|у), где")
    @Given("table {webElement} has {integerValue} row(s) with")
    public void filteredTableHasSize(WebElementParameter<WebTable> elementFinder,
                                     ValueIntegerParameter integerValue,
                                     WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filterBuilder(itemFilter)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("текстовая таблица {webElement} содержит {integerValue} строк(а|и|у)")
    @Given("text table {webElement} has {integerValue} row(s)")
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
    @Дано("текстовая таблица {webElement} содержит {integerValue} строк(а|и|у), где")
    @Given("text table {webElement} has {integerValue} row(s) with")
    public void filteredTextTableHasSize(WebElementParameter<WebTextTable> elementFinder,
                                         ValueIntegerParameter integerValue,
                                         WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filterBuilder(itemFilter)
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
    @Дано("в таблице {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the table {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection}")
    public void tableSorted(WebElementParameter<WebTable> elementFinder,
                            @SourceParameterReference("elementFinder") WebTableColumnParameter webTableColumn,
                            WebTableValueExtractorParameter valueExtractor,
                            @SourceParameterReference("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
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
     * @param valueExtractor -
     * @param blockElementFinder -
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Дано("в таблице {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection} в строках, где")
    @Given("in the table {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection} in rows with")
    public void filteredTableSorted(WebElementParameter<WebTable> elementFinder,
                                    @SourceParameterReference("elementFinder") WebTableColumnParameter webTableColumn,
                                    WebTableValueExtractorParameter valueExtractor,
                                    @SourceParameterReference("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                                    StringComparatorTypeParameter comparatorType,
                                    SortDirectionParameter sortDirection,
                                    WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTable.class)
                        .filterBuilder(itemFilter)
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
    @Дано("в текстовой таблице {webElement} в столбце {webTableColumn} значения в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the text table {webElement} in column {webTableColumn} values in {stringComparatorType} format sorted {sortDirection}")
    public void textTableSorted(WebElementParameter<WebTextTable> elementFinder,
                                @SourceParameterReference("elementFinder") WebTableColumnParameter webTableColumn,
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
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Дано("в текстовой таблице {webElement} в столбце {webTableColumn} значения в формате {stringComparatorType} отсортированы {sortDirection} в строках, где")
    @Given("in the text table {webElement} in column {webTableColumn} values in {stringComparatorType} format sorted {sortDirection} in rows with")
    public void filteredTextTableSorted(WebElementParameter<WebTextTable> elementFinder,
                                        @SourceParameterReference("elementFinder") WebTableColumnParameter webTableColumn,
                                        StringComparatorTypeParameter comparatorType,
                                        SortDirectionParameter sortDirection,
                                        WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filterBuilder(itemFilter)
                                .extractRows(webTableColumn.getRaw())
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

}
