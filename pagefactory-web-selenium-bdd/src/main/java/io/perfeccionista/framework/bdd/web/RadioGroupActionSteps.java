package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterBuilder;

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
                .forEachOrdered(element -> element.getByIndex(radioButtonIndex.getValue()).click());
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
                .forEachOrdered(element -> element.getByLabel(radioButtonLabel.getValue()).click());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to button")
    @Given("пользователь прокручивает {webElement} до кнопки")
    public void userScrollsRadioGroupToElement(WebElementParameter<WebRadioGroup> elementFinder,
                                               WebRadioButtonFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element.scrollToElement(itemFilter));
    }

}
