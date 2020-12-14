package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;

import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilder;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class RadioGroupActionSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param radioButtonIndex -
     */
    @Given("user selects in the {webElement} button with index {integerValue}")
    @Given("пользователь выбирает в {webElement} кнопку с индексом {integerValue}")
    public void userSelectRadioButtonWithIndex(WebElementParameter<WebRadioGroup> elementFinder,
                                               ValueIntegerParameter radioButtonIndex) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(webRadioGroupFilterBuilder()
                                        .add(radioButtonIndex(radioButtonIndex.getValue())))
                                .extractOne(element())
                                .should(haveNotNullResults()).getValue()
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
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(webRadioGroupFilterBuilder()
                                        .add(containsLabel(radioButtonLabel.getValue())))
                                .extractOne(element())
                                .should(haveNotNullResults()).getValue()
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
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebRadioGroup.class)
                                .filter(itemFilter)
                                .extractOne(element())
                                .should(haveNotNullResults()).getValue()
                                .scrollTo());
    }

}