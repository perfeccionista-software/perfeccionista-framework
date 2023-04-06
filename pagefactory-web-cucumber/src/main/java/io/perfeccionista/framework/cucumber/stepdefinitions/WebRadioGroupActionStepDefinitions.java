package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;

import static io.perfeccionista.framework.Web.*;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebRadioGroupActionStepDefinitions implements WebStepDefinitions {

//    /**
//     *
//     * @param elementFinder -
//     * @param radioButtonIndex -
//     */
//    @Дано("пользователь выбирает в радио-группе {webElement} кнопку с индексом {integerValue}")
//    @Given("user selects in the radio group {webElement} button with index {integerValue}")
//    public void userSelectRadioButtonWithIndex(WebElementParameter<WebList<WebRadioGroupBlock>> elementFinder,
//                                               ValueIntegerParameter radioButtonIndex) {
//        getWebPageContext().execute(context ->
//                        elementFinder.getElement(context, WebRadioGroup.class)
//                                .filterBuilder(webRadioGroupFilterBuilderWith(radioButtonIndex(radioButtonIndex.getValue())))
//                                .forSingle(WebRadioButton::click));
//    }
//
//    /**
//     *
//     * @param elementFinder -
//     * @param radioButtonLabel -
//     */
//    @Дано("пользователь выбирает в радио-группе {webElement} кнопку с лейблом {stringValue}")
//    @Given("user selects in the radio group {webElement} button with label {stringValue}")
//    public void userSelectRadioButtonWithLabel(WebElementParameter<WebRadioGroup> elementFinder,
//                                               ValueStringParameter radioButtonLabel) {
//        getWebPageContext().execute(context ->
//                        elementFinder.getElement(context, WebRadioGroup.class)
//                                .filterBuilder(webRadioGroupFilterBuilderWith(containsLabel(radioButtonLabel.getValue())))
//                                .forSingle(WebRadioButton::click));
//    }
//
//    /**
//     *
//     * @param elementFinder -
//     * @param itemFilter -
//     */
//    @Дано("пользователь прокручивает радио-группу {webElement} до кнопки, где")
//    @Given("user scrolls the radio group {webElement} to button with")
//    public void userScrollsRadioGroupToElement(WebElementParameter<WebRadioGroup> elementFinder,
//                                               WebRadioGroupFilterBuilder itemFilter) {
//        getWebPageContext().execute(context ->
//                        elementFinder.getElement(context, WebRadioGroup.class)
//                                .filterBuilder(itemFilter)
//                                .forSingle(WebRadioButton::scrollTo));
//    }

}
