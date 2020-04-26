package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.SortDirectionParameter;
import io.perfeccionista.framework.bdd.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableValueExtractorParameter;
import io.perfeccionista.framework.bdd.parameters.ref.SourceParameterRef;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class TableCheckSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("{webElement} has {integerValue} row(s)")
    @Given("{webElement} содержит {integerValue} строк(а|и)")
    public void tableHasSize(WebElementParameter<SizeAvailable> elementFinder,
                             ValueIntegerParameter integerValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .shouldHaveSize(integerValue.getValue()));
    }

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
                                     WebTableFilter itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .shouldHaveSize(integerValue.getValue()));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("{webElement} has {integerValue} row(s)")
    @Given("{webElement} содержится {integerValue} строк(а|и)")
    public void filteredTextTableHasSize(WebElementParameter<WebTextTable> elementFinder,
                                         ValueIntegerParameter integerValue,
                                         WebTextTableFilter itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .shouldHaveSize(integerValue.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element
                        .extractAllRows(valueExtractor.findForElement(webTableColumn.getRaw(), blockElementFinder.getRaw()))
                        .shouldBeSorted(comparatorType.findForDirection(sortDirection.getDirection())));
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
        elementFinder.find()
                .forEachOrdered(element -> element
                        .extractAllRows(webTableColumn.getRaw())
                        .shouldBeSorted(comparatorType.findForDirection(sortDirection.getDirection())));
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
                                    WebTableFilter itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .extractAllRows(valueExtractor.findForElement(webTableColumn.getRaw(), blockElementFinder.getRaw()))
                        .shouldBeSorted(comparatorType.findForDirection(sortDirection.getDirection())));
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
                                        WebTextTableFilter itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .extractAllRows(webTableColumn.getRaw())
                        .shouldBeSorted(comparatorType.findForDirection(sortDirection.getDirection())));
    }

}
