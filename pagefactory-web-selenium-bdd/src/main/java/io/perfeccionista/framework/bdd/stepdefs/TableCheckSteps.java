package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.SortDirectionParameter;
import io.perfeccionista.framework.bdd.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.bdd.parameters.ref.SourceParameterRef;
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
     * @param itemFilter -
     */
    @Given("{webElement} has {integerValue} row(s) with")
    @Given("{webElement} содержит {integerValue} строк(а|и), в которых")
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
     * @param itemFilter -
     */
    @Given("{webElement} has {integerValue} row(s), where")
    @Given("{webElement} содержится {integerValue} строк(а|и), где")
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
    @Given("in the {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection}")
    @Given("в {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection}")
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
    @Given("in the {webElement} in column {webTableColumn} values in {comparatorType} format sorted {sortDirection}")
    @Given("в {webElement} в столбце {webTableColumn} значения в формате {comparatorType} отсортированы {sortDirection}")
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
    @Given("in the {webElement} in column {webTableColumn} {webTableValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection} in rows with")
    @Given("в {webElement} в столбце {webTableColumn} {webTableValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection} в строках, в которых")
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
    @Given("in the {webElement} in column {webTableColumn} in {comparatorType} format sorted {sortDirection} values")
    @Given("в {webElement} в столбце {webTableColumn} в формате {comparatorType} отсортированы {sortDirection} значения")
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
