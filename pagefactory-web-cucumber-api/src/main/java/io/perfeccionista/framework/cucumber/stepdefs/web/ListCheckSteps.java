package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebListValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.ref.SourceParameterRef;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beClosed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beOpen;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.beSorted;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;

// TODO: Wrap runLogic() - и внутри создавать обернутый степ с проверками всех значений
// TODO: Add step categories
public class ListCheckSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is open")
    @Given("{webElement} раскрыт")
    public void listIsOpen(WebElementParameter<IsOpenAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsOpenAvailable.class)
                        .should(beOpen()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is closed")
    @Given("{webElement} закрыт")
    public void listIsClosed(WebElementParameter<IsOpenAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsOpenAvailable.class)
                        .should(beClosed()));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("list {webElement} has {integerValue} block(s)")
    @Given("список {webElement} содержит {integerValue} блок(а|ов)")
    public void listHasSize(WebElementParameter<WebList> elementFinder,
                            ValueIntegerParameter integerValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebList.class)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("list {webElement} has {integerValue} block(s) with")
    @Given("список {webElement} содержит {integerValue} блок(а|ов), где")
    public void filteredListHasSize(WebElementParameter<WebList> elementFinder,
                                    ValueIntegerParameter integerValue,
                                    WebListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebList.class)
                        .filter(itemFilter)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param extractorFinder -
     * @param blockElementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in list {webElement} {webListValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection}")
    @Given("в списке {webElement} {webListValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection}")
    public void listSorted(WebElementParameter<WebList> elementFinder,
                           WebListValueExtractorParameter extractorFinder,
                           @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                           StringComparatorTypeParameter comparatorType,
                           SortDirectionParameter sortDirection) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebList.class)
                                .extractAll(extractorFinder.createExtractorFor(blockElementFinder.getRaw()))
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
    @Given("in list {webElement} {webListValueExtractor} from {webBlockElement} in {comparatorType} format sorted {sortDirection} in blocks with")
    @Given("в списке {webElement} {webListValueExtractor} из {webBlockElement} в формате {comparatorType} отсортированы {sortDirection} в блоках, где")
    public void filteredListSorted(WebElementParameter<WebList> elementFinder,
                                   WebListValueExtractorParameter valueExtractor,
                                   @SourceParameterRef("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                                   StringComparatorTypeParameter comparatorType,
                                   SortDirectionParameter sortDirection,
                                   WebListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebList.class)
                                .filter(itemFilter)
                                .extractAll(valueExtractor.createExtractorFor(blockElementFinder.getRaw()))
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    // TextList

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("text list {webElement} has {integerValue} value(s)")
    @Given("текстовый список {webElement} содержит {integerValue} значени(е|я|й)")
    public void textListHasSize(WebElementParameter<WebTextList> elementFinder,
                                ValueIntegerParameter integerValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextList.class)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     * @param itemFilter -
     */
    @Given("text list {webElement} has {integerValue} value(s) with")
    @Given("текстовый список {webElement} содержит {integerValue} значени(е|я|й), где")             // Вопрос - можно ли одинаковые текстовки на разные сигнатуры
    public void filteredTextListHasSize(WebElementParameter<WebTextList> elementFinder,
                                        ValueIntegerParameter integerValue,
                                        WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextList.class)
                                .filter(itemFilter)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Given("in text list {webElement} values in {comparatorType} format sorted {sortDirection}")
    @Given("в текстовом списке {webElement} значения в формате {comparatorType} отсортированы {sortDirection}")
    public void textListSorted(WebElementParameter<WebTextList> elementFinder,
                               StringComparatorTypeParameter comparatorType,
                               SortDirectionParameter sortDirection) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextList.class)
                                .extractAll()
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     * @param itemFilter -
     */
    @Given("in text list {webElement} in {comparatorType} format sorted {sortDirection} values with")
    @Given("в текстовом списке {webElement} в формате {comparatorType} отсортированы {sortDirection} значения, где")
    public void filteredTextListSorted(WebElementParameter<WebTextList> elementFinder,
                                       StringComparatorTypeParameter comparatorType,
                                       SortDirectionParameter sortDirection,
                                       WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextList.class)
                                .filter(itemFilter)
                                .extractAll()
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

}
