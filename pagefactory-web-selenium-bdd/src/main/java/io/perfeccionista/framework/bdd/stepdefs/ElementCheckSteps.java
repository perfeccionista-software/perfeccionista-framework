package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.CssPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.DimensionsParameter;
import io.perfeccionista.framework.bdd.parameters.ColorParameter;
import io.perfeccionista.framework.bdd.parameters.ScreenshotParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beEnabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beSelected;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentNotBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentNotBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.havePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.looksLike;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeSelected;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHavePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notLooksLike;

// TODO: Написать в доке, что не все методы доступны для использования во множественном контексте
// TODO: Add to exception context limiter information
// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class ElementCheckSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} exists")
    @Given("{webElement} присутствует")
    public void elementExists(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(bePresent()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} does not exist")
    @Given("{webElement} отсутствует")
    public void elementDoesNotExist(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notBePresent()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is displayed")
    @Given("{webElement} отображается")
    public void elementIsDisplayed(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(beDisplayed()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not displayed")
    @Given("{webElement} не отображается")
    public void elementIsNotDisplayed(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notBeDisplayed()));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("{webElement} contains {stringValue}")
    @Given("{webElement} содержит {stringValue}")
    public void elementContainsText(WebElementParameter<GetTextAvailable> elementFinder,
                                    ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetTextAvailable.class)
                        .should(haveText(expectedText.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("{webElement} does not contain {stringValue}")
    @Given("{webElement} не содержит {stringValue}")
    public void elementDoesNotContainText(WebElementParameter<GetTextAvailable> elementFinder,
                                          ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetTextAvailable.class)
                        .should(notHaveText(expectedText.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("{webElement} contains number {numberValue}")
    @Given("{webElement} содержит число {numberValue}")
    public void elementContainsNumber(WebElementParameter<GetTextAvailable> elementFinder,
                                      ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetTextAvailable.class)
                        .should(haveText(expectedNumber.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("{webElement} does not contain number {numberValue}")
    @Given("{webElement} не содержит число {numberValue}")
    public void elementDoesNotContainNumber(WebElementParameter<GetTextAvailable> elementFinder,
                                            ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetTextAvailable.class)
                        .should(notHaveText(expectedNumber.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("label of the {webElement} contain {stringValue}")
    @Given("лейбл {webElement} содержит {stringValue}")
    public void labelOfTheElementContainText(WebElementParameter<GetLabelAvailable> elementFinder,
                                             ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetLabelAvailable.class)
                        .should(haveLabel(expectedText.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("label of the {webElement} does not contain {stringValue}")
    @Given("лейбл {webElement} не содержит {stringValue}")
    public void labelOfTheElementDoesNotContainText(WebElementParameter<GetLabelAvailable> elementFinder,
                                                    ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetLabelAvailable.class)
                        .should(notHaveLabel(expectedText.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("label of the {webElement} contain number {numberValue}")
    @Given("лейбл {webElement} содержит число {numberValue}")
    public void labelOfTheElementContainNumber(WebElementParameter<GetLabelAvailable> elementFinder,
                                              ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetLabelAvailable.class)
                        .should(haveLabel(expectedNumber.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("label of the {webElement} does not contain number {numberValue}")
    @Given("лейбл {webElement} не содержит число {numberValue}")
    public void labelOfTheElementDoesNotContainNumber(WebElementParameter<GetLabelAvailable> elementFinder,
                                                      ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetLabelAvailable.class)
                        .should(notHaveLabel(expectedNumber.getValue())));
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("property {webElementProperty} of the {webElement} contains {stringValue}")
    @Given("свойство {webElementProperty} элемента {webElement} содержит {stringValue}")
    public void propertyOfTheElementContainText(WebElementPropertyParameter elementProperty,
                                                WebElementParameter<WebChildElement> elementFinder,
                                                ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(havePropertyValue(elementProperty.getRaw(), expectedText.getValue())));
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("property {webElementProperty} of the {webElement} does not contain {stringValue}")
    @Given("свойство {webElementProperty} элемента {webElement} не содержит {stringValue}")
    public void propertyOfTheElementDoesNotContainText(WebElementPropertyParameter elementProperty,
                                                       WebElementParameter<WebChildElement> elementFinder,
                                                       ValueStringParameter expectedText) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHavePropertyValue(elementProperty.getRaw(), expectedText.getValue())));
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("property {webElementProperty} of the {webElement} contains number {numberValue}")
    @Given("свойство {webElementProperty} элемента {webElement} содержит число {numberValue}")
    public void propertyOfTheElementContainNumber(WebElementPropertyParameter elementProperty,
                                                  WebElementParameter<WebChildElement> elementFinder,
                                                  ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(havePropertyValue(elementProperty.getRaw(), expectedNumber.getValue())));
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("property {webElementProperty} of the {webElement} does not contain number {numberValue}")
    @Given("свойство {webElementProperty} элемента {webElement} не содержит число {numberValue}")
    public void propertyOfTheElementDoesNotContainNumber(WebElementPropertyParameter elementProperty,
                                                         WebElementParameter<WebChildElement> elementFinder,
                                                         ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHavePropertyValue(elementProperty.getRaw(), expectedNumber.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param elementComponent -
     */
    @Given("component {webElementComponent} of the {webElement} is present")
    @Given("компонент {webElementComponent} элемента {webElement} присутствует")
    public void elementComponentIsPresent(WebElementComponentParameter elementComponent,
                                          WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(componentBePresent(elementComponent.getRaw())));
    }

    /**
     *
     * @param elementFinder -
     * @param elementComponent -
     */
    @Given("component {webElementComponent} of the {webElement} is not present")
    @Given("компонент {webElementComponent} элемента {webElement} отсутствует")
    public void elementComponentIsNotPresent(WebElementComponentParameter elementComponent,
                                             WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(componentNotBePresent(elementComponent.getRaw())));
    }

    /**
     *
     * @param elementFinder -
     * @param elementComponent -
     */
    @Given("component {webElementComponent} of the {webElement} is displayed")
    @Given("компонент {webElementComponent} элемента {webElement} отображается")
    public void elementComponentIsDisplayed(WebElementComponentParameter elementComponent,
                                            WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(componentBeDisplayed(elementComponent.getRaw())));
    }

    /**
     *
     * @param elementFinder -
     * @param elementComponent -
     */
    @Given("component {webElementComponent} of the {webElement} is not displayed")
    @Given("компонент {webElementComponent} элемента {webElement} не отображается")
    public void elementComponentIsNotDisplayed(WebElementComponentParameter elementComponent,
                                               WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(componentNotBeDisplayed(elementComponent.getRaw())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedColor -
     */
    @Given("component {webElementComponent} of the {webElement} has color {color} of css-property {cssProperty}")
    @Given("компонент {webElementComponent} элемента {webElement} имеет цвет {color} css-свойства {cssProperty}")
    public void elementComponentHasColor(WebElementComponentParameter elementComponent,
                                         WebElementParameter<WebChildElement> elementFinder,
                                         ColorParameter expectedColor,
                                         CssPropertyParameter cssProperty) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveColor(elementComponent.getRaw(), cssProperty.getRaw(), expectedColor.getColor())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedColor -
     */
    @Given("component {webElementComponent} of the {webElement} does not have color {color} of css-property {cssProperty}")
    @Given("компонент {webElementComponent} элемента {webElement} не имеет цвет {color} css-свойства {cssProperty}")
    public void elementComponentDoesNotHaveColor(WebElementComponentParameter elementComponent,
                                                 WebElementParameter<WebChildElement> elementFinder,
                                                 ColorParameter expectedColor,
                                                 CssPropertyParameter cssProperty) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveColor(elementComponent.getRaw(), cssProperty.getRaw(), expectedColor.getColor())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedDimensions -
     */
    @Given("component {webElementComponent} of the {webElement} has dimensions {dimensions}")
    @Given("компонент {webElementComponent} элемента {webElement} имеет размеры {dimensions}")
    public void elementComponentHasBounds(WebElementComponentParameter elementComponent,
                                          WebElementParameter<WebChildElement> elementFinder,
                                          DimensionsParameter expectedDimensions) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveDimensions(elementComponent.getRaw(), expectedDimensions.getDimensions())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedDimensions -
     */
    @Given("component {webElementComponent} of the {webElement} does not have dimensions {dimensions}")
    @Given("компонент {webElementComponent} элемента {webElement} не имеет размеры {dimensions}")
    public void elementComponentDoesNotHaveBounds(WebElementComponentParameter elementComponent,
                                                  WebElementParameter<WebChildElement> elementFinder,
                                                  DimensionsParameter expectedDimensions) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveDimensions(elementComponent.getRaw(), expectedDimensions.getDimensions())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedScreenshot -
     */
    @Given("component {webElementComponent} of the {webElement} looks like {screenshot}")
    @Given("компонент {webElementComponent} элемента {webElement} выглядит как {screenshot}")
    public void elementComponentLooksLikeScreenshot(WebElementComponentParameter elementComponent,
                                                    WebElementParameter<WebChildElement> elementFinder,
                                                    ScreenshotParameter expectedScreenshot) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(looksLike(elementComponent.getRaw(), expectedScreenshot.getScreenshot())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedScreenshot -
     */
    @Given("component {webElementComponent} of the {webElement} does not looks like {screenshot}")
    @Given("компонент {webElementComponent} элемента {webElement} не выглядит как {screenshot}")
    public void elementComponentDoesNotLooksLikeScreenshot(WebElementComponentParameter elementComponent,
                                                           WebElementParameter<WebChildElement> elementFinder,
                                                           ScreenshotParameter expectedScreenshot) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notLooksLike(elementComponent.getRaw(), expectedScreenshot.getScreenshot())));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is enabled")
    @Given("{webElement} доступ(ен|но|на)")
    public void elementIsEnabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsEnabledAvailable.class)
                        .should(beEnabled()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is disabled")
    @Given("{webElement} недоступ(ен|но|на)")
    public void elementIsDisabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsEnabledAvailable.class)
                        .should(beDisabled()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is selected")
    @Given("{webElement} выделен(а|о)")
    public void elementIsSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsSelectedAvailable.class)
                        .should(beSelected()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not selected")
    @Given("{webElement} не выделен(а|о)")
    public void elementIsNotSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsSelectedAvailable.class)
                        .should(notBeSelected()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is in focus")
    @Given("{webElement} находится в фокусе")
    public void elementIsInFocus(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(beInFocus()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not in focus")
    @Given("{webElement} находится не в фокусе")
    public void elementIsNotInFocus(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notBeInFocus()));
    }

}
