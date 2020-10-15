package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beEnabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beSelected;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeSelected;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveIndex;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class RadioGroupCheckSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("{webElement} has {integerValue} button(s)")
    @Given("в {webElement} содержится {integerValue} кноп(ка|ки|ок)")
    public void radioGroupHasSize(WebElementParameter<WebRadioGroup> elementFinder,
                                  ValueIntegerParameter integerValue) {
        elementFinder.find()
                .forEachOrdered(element -> element.should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param integerValue -
     */
    @Given("{webElement} has {integerValue} button(s), where")
    @Given("в {webElement} содержится {integerValue} кноп(ка|ки|ок), где")
    public void filteredRadioGroupHasSize(WebElementParameter<WebRadioGroup> elementFinder,
                                          ValueIntegerParameter integerValue,
                                          WebRadioGroupFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(itemFilter)
                        .should(haveSize(integerValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} has button with label {stringValue}")
    @Given("в {webElement} кнопка с лейблом {stringValue} присутствует")
    public void radioGroupHasButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                             ValueStringParameter labelValue) {
        NumberValue<Integer> expectedCount = getEnvironment().getService(ValueService.class).intEquals(1);
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .should(haveSize(expectedCount)));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} has button with index {integerValue}")
    @Given("в {webElement} кнопка с индексом {integerValue} присутствует")
    public void radioGroupHasButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                             ValueIntegerParameter indexValue) {
        NumberValue<Integer> expectedCount = getEnvironment().getService(ValueService.class).intEquals(1);
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .should(haveSize(expectedCount)));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} does not have button with label {stringValue}")
    @Given("в {webElement} кнопка с лейблом {stringValue} отсутствует")
    public void radioGroupDoesNotHaveButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueStringParameter labelValue) {
        NumberValue<Integer> expectedCount = getEnvironment().getService(ValueService.class).intEquals(0);
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .should(haveSize(expectedCount)));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} does not have button with index {integerValue}")
    @Given("в {webElement} кнопка с индексом {integerValue} отсутствует")
    public void radioGroupDoesNotHaveButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueIntegerParameter indexValue) {
        NumberValue<Integer> expectedCount = getEnvironment().getService(ValueService.class).intEquals(0);
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .should(haveSize(expectedCount)));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} has a button with label {stringValue} which is selected")
    @Given("в {webElement} кнопка с лейблом {stringValue} выделена")
    public void radioGroupHasSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueStringParameter labelValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beSelected()));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} has a button with index {integerValue} which is selected")
    @Given("в {webElement} кнопка с индексом {integerValue} выделена")
    public void radioGroupHasSelectedButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                     ValueIntegerParameter indexValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beSelected()));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} has a button with label {stringValue} which is not selected")
    @Given("в {webElement} кнопка с лейблом {stringValue} не выделена")
    public void radioGroupDoesNotHaveSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                             ValueStringParameter labelValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(notBeSelected()));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} has a button with index {integerValue} which is not selected")
    @Given("в {webElement} кнопка с индексом {integerValue} не выделена")
    public void radioGroupDoesNotHaveSelectedButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                             ValueIntegerParameter indexValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(notBeSelected()));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} has a button with label {stringValue} which is enabled")
    @Given("в {webElement} кнопка с лейблом {stringValue} доступна")
    public void radioGroupHasEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                    ValueStringParameter labelValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beEnabled()));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} has a button with index {integerValue} which is enabled")
    @Given("в {webElement} кнопка с индексом {integerValue} доступна")
    public void radioGroupHasEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                    ValueIntegerParameter indexValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beEnabled()));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     */
    @Given("{webElement} has a button with label {stringValue} which is disabled")
    @Given("в {webElement} кнопка с лейблом {stringValue} недоступна")
    public void radioGroupDoesNotHaveEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                            ValueStringParameter labelValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beDisabled()));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     */
    @Given("{webElement} has a button with index {integerValue} which is disabled")
    @Given("в {webElement} кнопка с индексом {integerValue} недоступна")
    public void radioGroupDoesNotHaveEnabledButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                            ValueIntegerParameter indexValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .extractOne(element()).getValue()
                        .should(beDisabled()));
    }

    /**
     *
     * @param elementFinder -
     * @param labelValue -
     * @param indexValue -
     */
    @Given("{webElement} has a button with label {stringValue} which has index {integerValue}")
    @Given("в {webElement} кнопка с лейблом {stringValue} имеет индекс {integerValue}")
    public void radioGroupHasButtonWithLabelWhichHasIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                                          ValueStringParameter labelValue,
                                                          ValueIntegerParameter indexValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(containsLabel(labelValue.getValue())))
                        .should(haveIndex(indexValue.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param indexValue -
     * @param labelValue -
     */
    @Given("{webElement} has a button with index {integerValue} which has label {stringValue}")
    @Given("в {webElement} кнопка с индексом {integerValue} имеет лейбл {stringValue}")
    public void radioGroupHasButtonWithIndexWhichHasLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                                          ValueIntegerParameter indexValue,
                                                          ValueStringParameter labelValue) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .filter(with(radioButtonIndex(indexValue.getValue())))
                        .extractOne(element())
                        .should(haveNotNullResults())
                        .getValue()
                        .should(haveLabel(labelValue.getValue())));
    }

}
