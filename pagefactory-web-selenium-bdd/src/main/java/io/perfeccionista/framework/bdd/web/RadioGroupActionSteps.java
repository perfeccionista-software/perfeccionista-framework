package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;

import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilder;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class RadioGroupActionSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     * @param radioButtonIndex -
     */
    @Given("user selects in the {webElement} button with index {integerValue}")
    @Given("пользователь выбирает в {webElement} кнопку с индексом {integerValue}")
    public void userSelectRadioButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                               ValueIntegerParameter radioButtonIndex) {
        elementFinder.find()
                .forEachOrdered(element -> element.filter(webRadioGroupFilterBuilder()
                        .add(radioButtonIndex(radioButtonIndex.getValue())))
                        .extractOne(element())
                        .should(haveNotNullResults())
                        .getValue()
                        .click());
    }

    /**
     *
     * @param elementFinder -
     * @param radioButtonLabel -
     */
    @Given("user selects in the {webElement} button with label {stringValue}")
    @Given("пользователь выбирает в {webElement} кнопку с лейблом {stringValue}")
    public void userSelectRadioButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                               ValueStringParameter radioButtonLabel) {
        elementFinder.find()
                .forEachOrdered(element -> element.filter(webRadioGroupFilterBuilder()
                        .add(containsLabel(radioButtonLabel.getValue())))
                        .extractOne(element())
                        .should(haveNotNullResults())
                        .getValue()
                        .click());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to button")
    @Given("пользователь прокручивает {webElement} до кнопки")
    public void userScrollsRadioGroupToElement(WebElementParameter<WebRadioGroup> elementFinder,
                                               WebRadioGroupFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element.filter(itemFilter)
                        .extractOne(element())
                        .should(haveNotNullResults())
                        .getValue()
                        .click());
    }

}