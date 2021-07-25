package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBlockElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebListBlockValueExtractorParameter;
import io.perfeccionista.framework.cucumber.parameters.reference.SourceParameterReference;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;

import static io.perfeccionista.framework.Web.*;

// TODO: Add step categories
public class WebListCheckStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} раскрыт")
    @Given("element {webElement} is open")
    public void listIsOpen(WebElementParameter<WebDropDownAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebDropDownAvailable.class)
                        .should(beOpen()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} закрыт")
    @Given("element {webElement} is closed")
    public void listIsClosed(WebElementParameter<WebDropDownAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebDropDownAvailable.class)
                        .should(beClosed()));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("таблица {webElement} содержит {integerValue} строк(а|и|у)")
    @Given("table {webElement} has {integerValue} row(s)")
    @Дано("список {webElement} содержит {integerValue} блок(а|ов)")
    @Given("list {webElement} has {integerValue} block(s)")
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
    @Дано("таблица {webElement} содержит {integerValue} строк(а|и|у), где")
    @Given("table {webElement} has {integerValue} row(s) with")
    @Дано("список {webElement} содержит {integerValue} блок(а|ов), где")
    @Given("list {webElement} has {integerValue} block(s) with")
    public void filteredListHasSize(WebElementParameter<WebList> elementFinder,
                                    ValueIntegerParameter integerValue,
                                    WebBlockFilterBuilder<?> itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebList.class)
                        .filter(block -> itemFilter)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("текстовый список {webElement} содержит {integerValue} значени(е|я|й)")
    @Given("text list {webElement} has {integerValue} value(s)")
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
    @Дано("текстовый список {webElement} содержит {integerValue} значени(е|я|й), где")
    @Given("text list {webElement} has {integerValue} value(s) with")
    public void filteredTextListHasSize(WebElementParameter<WebTextList> elementFinder,
                                        ValueIntegerParameter integerValue,
                                        WebTextBlockFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTextList.class)
                        .filterBuilder(itemFilter)
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
    @Дано("в таблице {webElement} {webListValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the table {webElement} {webListValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection}")
    @Дано("в списке {webElement} {webListValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the list {webElement} {webListValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection}")
    public void listSorted(WebElementParameter<WebList> elementFinder,
                           WebListBlockValueExtractorParameter extractorFinder,
                           @SourceParameterReference("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
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
    @Дано("в таблице {webElement} {webListValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection} в строках, где")
    @Given("in the table {webElement} {webListValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection} in rows with")
    @Дано("в списке {webElement} {webListValueExtractor} из {webBlockElement} в формате {stringComparatorType} отсортированы {sortDirection} в блоках, где")
    @Given("in the list {webElement} {webListValueExtractor} from {webBlockElement} in {stringComparatorType} format sorted {sortDirection} in blocks with")
    public void filteredListSorted(WebElementParameter<WebList> elementFinder,
                                   WebListBlockValueExtractorParameter valueExtractor,
                                   @SourceParameterReference("elementFinder") WebBlockElementParameter<WebChildElement> blockElementFinder,
                                   StringComparatorTypeParameter comparatorType,
                                   SortDirectionParameter sortDirection,
                                   WebBlockFilterBuilder<?> itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebList.class)
                                .filter(block -> itemFilter)
                                .extractAll(valueExtractor.createExtractorFor(blockElementFinder.getRaw()))
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Дано("в текстовом списке {webElement} значения в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the text list {webElement} in {stringComparatorType} format sorted {sortDirection} values")
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
    @Дано("в текстовом списке {webElement} значения в формате {stringComparatorType} отсортированы {sortDirection}, где")
    @Given("in the text list {webElement} in {stringComparatorType} format sorted {sortDirection} values with")
    public void filteredTextListSorted(WebElementParameter<WebTextList> elementFinder,
                                       StringComparatorTypeParameter comparatorType,
                                       SortDirectionParameter sortDirection,
                                       WebTextBlockFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextList.class)
                                .filterBuilder(itemFilter)
                                .extractAll()
                                .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

}
