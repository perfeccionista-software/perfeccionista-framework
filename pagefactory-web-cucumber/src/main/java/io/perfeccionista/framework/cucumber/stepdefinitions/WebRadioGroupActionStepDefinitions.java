package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilder;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebRadioGroupActionStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param radioButtonIndex -
     */
    @Дано("пользователь выбирает в радио-группе {webElement} кнопку с индексом {integerValue}")
    @Given("user selects in the radio group {webElement} button with index {integerValue}")
    public void userSelectRadioButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                               ValueIntegerParameter radioButtonIndex) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(webRadioGroupFilterBuilder()
                                        .add(radioButtonIndex(radioButtonIndex.getValue())))
                                .extractOne(element())
                                .getNotNullResult()
                                .click());
    }

    /**
     *
     * @param elementFinder -
     * @param radioButtonLabel -
     */
    @Дано("пользователь выбирает в радио-группе {webElement} кнопку с лейблом {stringValue}")
    @Given("user selects in the radio group {webElement} button with label {stringValue}")
    public void userSelectRadioButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
                                               ValueStringParameter radioButtonLabel) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(webRadioGroupFilterBuilder()
                                        .add(containsLabel(radioButtonLabel.getValue())))
                                .extractOne(element())
                                .getNotNullResult()
                                .click());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь прокручивает радио-группу {webElement} до кнопки, где")
    @Given("user scrolls the radio group {webElement} to button with")
    public void userScrollsRadioGroupToElement(WebElementParameter<WebRadioGroup> elementFinder,
                                               WebRadioGroupFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(itemFilter)
                                .extractOne(element())
                                .getNotNullResult()
                                .scrollTo());
    }

}
