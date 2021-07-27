package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.SortDirectionParameter;
import io.perfeccionista.framework.cucumber.parameters.StringComparatorTypeParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebRadioButtonValueExtractorParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intEquals;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebRadioGroupCheckStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("в радио-группе {webElement} содержится {integerValue} кноп(ка|ки|ок)")
    @Given("radio group {webElement} has {integerValue} button(s)")
    public void radioGroupHasSize(WebElementParameter<WebRadioGroup> elementFinder,
                                  ValueIntegerParameter integerValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Дано("в радио-группе {webElement} содержится {integerValue} кноп(ка|ки|ок), где")
    @Given("radio group {webElement} has {integerValue} button(s), where")
    public void filteredRadioGroupHasSize(WebElementParameter<WebRadioGroup> elementFinder,
                                          ValueIntegerParameter integerValue,
                                          WebRadioGroupFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(itemFilter)
                                .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} присутствует")
    @Given("radio group {webElement} has button with label {stringValue}")
    public void radioGroupHasButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                             ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .should(haveSize(intEquals(1))));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} отсутствует")
    @Given("radio group {webElement} does not have button with label {stringValue}")
    public void radioGroupDoesNotHaveButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .should(haveSize(intEquals(0))));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} выделена")
    @Given("radio group {webElement} has a button with label {stringValue} which is selected")
    public void radioGroupHasSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .forSingle(webRadioButton -> webRadioButton.should(beSelected())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} не выделена")
    @Given("radio group {webElement} has a button with label {stringValue} which is not selected")
    public void radioGroupDoesNotHaveSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                             ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .forSingle(webRadioButton -> webRadioButton.should(notBeSelected())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} доступна")
    @Given("radio group {webElement} has a button with label {stringValue} which is enabled")
    public void radioGroupHasEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                    ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .forSingle(webRadioButton -> webRadioButton.should(beEnabled())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} недоступна")
    @Given("radio group {webElement} has a button with label {stringValue} which is disabled")
    public void radioGroupDoesNotHaveEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                            ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .forSingle(webRadioButton -> webRadioButton.should(beDisabled())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с лейблом {stringValue} имеет индекс {integerValue}")
    @Given("radio group {webElement} has a button with label {stringValue} which has index {integerValue}")
    public void radioGroupHasButtonWithLabelWhichHasIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                          ValueStringParameter labelValue,
                                                          ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .filterBuilder(with(containsLabel(labelValue.getValue())))
                        .should(haveIndex(indexValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} присутствует")
    @Given("radio group {webElement} has button with index {integerValue}")
    public void radioGroupHasButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                             ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .should(haveSize(intEquals(1))));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} отсутствует")
    @Given("radio group {webElement} does not have button with index {integerValue}")
    public void radioGroupDoesNotHaveButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .should(haveSize(intEquals(0))));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} выделена")
    @Given("radio group {webElement} has a button with index {integerValue} which is selected")
    public void radioGroupHasSelectedButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .forSingle(webRadioButton -> webRadioButton.should(beSelected())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} не выделена")
    @Given("radio group {webElement} has a button with index {integerValue} which is not selected")
    public void radioGroupDoesNotHaveSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                             ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .forSingle(webRadioButton -> webRadioButton.should(notBeSelected())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} доступна")
    @Given("radio group {webElement} has a button with index {integerValue} which is enabled")
    public void radioGroupHasEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                    ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .forSingle(webRadioButton -> webRadioButton.should(beEnabled())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} недоступна")
    @Given("radio group {webElement} has a button with index {integerValue} which is disabled")
    public void radioGroupDoesNotHaveEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                            ValueIntegerParameter indexValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .forSingle(webRadioButton -> webRadioButton.should(beDisabled())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     * @param labelValue -
     */
    @Дано("в радио-группе {webElement} кнопка с индексом {integerValue} имеет лейбл {stringValue}")
    @Given("radio group {webElement} has a button with index {integerValue} which has label {stringValue}")
    public void radioGroupHasButtonWithIndexWhichHasLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                          ValueIntegerParameter indexValue,
                                                          ValueStringParameter labelValue) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filterBuilder(with(radioButtonIndex(indexValue.getValue())))
                                .forSingle(webRadioButton -> webRadioButton.should(haveLabel(labelValue.getValue()))));
    }

    /**
     *
     * @param elementFinder -
     * @param comparatorType -
     * @param sortDirection -
     */
    @Дано("в радио-группе {webElement} {webRadioButtonValueExtractor} в формате {stringComparatorType} отсортированы {sortDirection}")
    @Given("in the radio group {webElement} {webRadioButtonValueExtractor} in {stringComparatorType} format sorted {sortDirection}")
    public void radioGroupSorted(WebElementParameter<WebRadioGroup> elementFinder,
                                 WebRadioButtonValueExtractorParameter valueExtractor,
                                 StringComparatorTypeParameter comparatorType,
                                 SortDirectionParameter sortDirection) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebRadioGroup.class)
                        .extractAll(valueExtractor.createExtractor())
                        .should(beSorted(comparatorType.findComparatorForDirection(sortDirection.getDirection()))));
    }

}
