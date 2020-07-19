package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.SortDirectionParameter;
import io.perfeccionista.framework.bdd.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebListValueExtractorParameter;
import io.perfeccionista.framework.bdd.parameters.ref.SourceParameterRef;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class ListCheckSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is open")
    @Given("{webElement} раскрыт")
    public void listIsOpen(WebElementParameter<IsOpenAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsOpenAvailable::shouldBeOpen);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is closed")
    @Given("{webElement} закрыт")
    public void listIsClosed(WebElementParameter<IsOpenAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsOpenAvailable::shouldBeClosed);
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("{webElement} has {integerValue} block(s)")
    @Given("{webElement} содержит {integerValue} блок(а|ов)")
    public void listHasSize(WebElementParameter<SizeAvailable> elementFinder,
                            ValueIntegerParameter integerValue) {
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveSize(integerValue.getValue()));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("{webElement} has {integerValue} block(s) with")
    @Given("{webElement} содержит {integerValue} блок(а|ов), в котор(ом|ых)")
    public void filteredListHasSize(WebElementParameter<WebList> elementFinder,
                                    ValueIntegerParameter integerValue,
                                    WebListFilterBuilder itemFilter) {
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
    @Given("{webElement} has {integerValue} value(s) with")
    @Given("{webElement} содержит {integerValue} значени(е|я|й)")
    public void filteredTextListHasSize(WebElementParameter<WebTextList> elementFinder,
                                        ValueIntegerParameter integerValue,
                                        WebTextListFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .shouldHaveSize(integerValue.getValue()));
    }

    /**
     *
     * @param elementFinder -
     * @param extractorFinder -
     * @param blockElementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in the {webElement} {webListValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection}")
    @Given("в {webElement} {webListValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection}")
    public void listSorted(WebElementParameter<WebList> elementFinder,
                           WebListValueExtractorParameter extractorFinder,
                           @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                           StringComparatorTypeParameter comparatorType,
                           SortDirectionParameter sortDirection) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .extractAll(extractorFinder.createExtractorFor(blockElementFinder.getRaw()))
                        .shouldBeSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection())));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in the {webElement} values in {comparatorType} format sorted {sortDirection}")
    @Given("в {webElement} значения в формате {comparatorType} отсортированы {sortDirection}")
    public void textListSorted(WebElementParameter<WebTextList> elementFinder,
                               StringComparatorTypeParameter comparatorType,
                               SortDirectionParameter sortDirection) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .extractAll()
                        .shouldBeSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection())));
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
    @Given("in the {webElement} {webListValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection} in blocks with")
    @Given("в {webElement} {webListValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection} в блоках, в которых")
    public void filteredListSorted(WebElementParameter<WebList> elementFinder,
                                   WebListValueExtractorParameter valueExtractor,
                                   @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                                   StringComparatorTypeParameter comparatorType,
                                   SortDirectionParameter sortDirection,
                                   WebListFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .extractAll(valueExtractor.createExtractorFor(blockElementFinder.getRaw()))
                        .shouldBeSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection())));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Given("in the {webElement} in {comparatorType} format sorted {sortDirection} values")
    @Given("в {webElement} в формате {comparatorType} отсортированы {sortDirection} значения")
    public void filteredTextListSorted(WebElementParameter<WebTextList> elementFinder,
                                       StringComparatorTypeParameter comparatorType,
                                       SortDirectionParameter sortDirection,
                                       WebTextListFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .extractAll()
                        .shouldBeSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection())));
    }

}
