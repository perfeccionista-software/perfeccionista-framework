package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.CssPropertyParameter;
import io.perfeccionista.framework.cucumber.parameters.DimensionsParameter;
import io.perfeccionista.framework.cucumber.parameters.ColorParameter;
import io.perfeccionista.framework.cucumber.parameters.LocationParameter;
import io.perfeccionista.framework.cucumber.parameters.ScreenshotParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueNumberParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementComponentParameter;
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
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
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
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHavePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notLooksLike;

// TODO: Написать в доке, что не все методы доступны для использования во множественном контексте
// TODO: Add to exception context limiter information
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class WebElementCheckStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} присутствует")
    @Given("element {webElement} is present")
    public void elementExists(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(bePresent()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} отсутствует")
    @Given("element {webElement} is not present")
    public void elementDoesNotExist(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notBePresent()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} отображается")
    @Given("element {webElement} is displayed")
    public void elementIsDisplayed(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(beDisplayed()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} не отображается")
    @Given("element {webElement} is not displayed")
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
    @Дано("элемент {webElement} содержит {stringValue}")
    @Given("element {webElement} contains {stringValue}")
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
    @Дано("элемент {webElement} не содержит {stringValue}")
    @Given("element {webElement} does not contain {stringValue}")
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
    @Дано("элемент {webElement} содержит число {numberValue}")
    @Given("element {webElement} contains number {numberValue}")
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
    @Дано("элемент {webElement} не содержит число {numberValue}")
    @Given("element {webElement} does not contain number {numberValue}")
    public void elementDoesNotContainNumber(WebElementParameter<GetTextAvailable> elementFinder,
                                            ValueNumberParameter expectedNumber) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, GetTextAvailable.class)
                        .should(notHaveText(expectedNumber.getValue())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedColor -
     */
    @Дано("элемент {webElement} имеет цвет {color} css-свойства {cssProperty}")
    @Given("element {webElement} has color {color} of css-property {cssProperty}")
    public void elementHasColor(WebElementParameter<WebChildElement> elementFinder,
                                ColorParameter expectedColor,
                                CssPropertyParameter cssProperty) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveColor(cssProperty.getRaw(), expectedColor.getColor())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedColor -
     */
    @Дано("элемент {webElement} не имеет цвет {color} css-свойства {cssProperty}")
    @Given("element {webElement} does not have color {color} of css-property {cssProperty}")
    public void elementDoesNotHaveColor(WebElementParameter<WebChildElement> elementFinder,
                                        ColorParameter expectedColor,
                                        CssPropertyParameter cssProperty) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveColor(cssProperty.getRaw(), expectedColor.getColor())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedDimensions -
     */
    @Дано("элемент {webElement} имеет размеры {dimensions}")
    @Given("element {webElement} has dimensions {dimensions}")
    public void elementHasDimensions(WebElementParameter<WebChildElement> elementFinder,
                                     DimensionsParameter expectedDimensions) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveDimensions(expectedDimensions.getDimensions())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedDimensions -
     */
    @Дано("элемент {webElement} не имеет размеры {dimensions}")
    @Given("element {webElement} does not have dimensions {dimensions}")
    public void elementDoesNotHaveDimensions(WebElementParameter<WebChildElement> elementFinder,
                                             DimensionsParameter expectedDimensions) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveDimensions(expectedDimensions.getDimensions())));
    }


    /**
     *
     * @param elementFinder -
     * @param expectedLocation -
     */
    @Дано("элемент {webElement} находится в {location}")
    @Given("element {webElement} has location {location}")
    public void elementHasLocation(WebElementParameter<WebChildElement> elementFinder,
                                   LocationParameter expectedLocation) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveLocation(expectedLocation.getLocation())));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedLocation -
     */
    @Дано("элемент {webElement} не находится в {location}")
    @Given("element {webElement} does not have location {location}")
    public void elementDoesNotHaveLocation(WebElementParameter<WebChildElement> elementFinder,
                                           LocationParameter expectedLocation) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveLocation(expectedLocation.getLocation())));
    }


    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} доступен")
    @Given("element {webElement} is enabled")
    public void elementIsEnabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsEnabledAvailable.class)
                        .should(beEnabled()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} недоступен")
    @Given("element {webElement} is disabled")
    public void elementIsDisabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsEnabledAvailable.class)
                        .should(beDisabled()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} выделен")
    @Given("element {webElement} is selected")
    public void elementIsSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsSelectedAvailable.class)
                        .should(beSelected()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} не выделен")
    @Given("element {webElement} is not selected")
    public void elementIsNotSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, IsSelectedAvailable.class)
                        .should(notBeSelected()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} находится в фокусе")
    @Given("element {webElement} is in focus")
    public void elementIsInFocus(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(beInFocus()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("элемент {webElement} находится не в фокусе")
    @Given("element {webElement} is not in focus")
    public void elementIsNotInFocus(WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notBeInFocus()));
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Дано("лейбл элемента {webElement} содержит {stringValue}")
    @Given("label of the element {webElement} contains {stringValue}")
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
    @Дано("лейбл элемента {webElement} не содержит {stringValue}")
    @Given("label of the element {webElement} does not contain {stringValue}")
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
    @Дано("лейбл элемента {webElement} содержит число {numberValue}")
    @Given("label of the element {webElement} contains number {numberValue}")
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
    @Дано("лейбл элемента {webElement} не содержит число {numberValue}")
    @Given("label of the element {webElement} does not contain number {numberValue}")
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
    @Дано("свойство {webElementProperty} элемента {webElement} содержит {stringValue}")
    @Given("property {webElementProperty} of the element {webElement} contains {stringValue}")
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
    @Дано("свойство {webElementProperty} элемента {webElement} не содержит {stringValue}")
    @Given("property {webElementProperty} of the element {webElement} does not contain {stringValue}")
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
    @Дано("свойство {webElementProperty} элемента {webElement} содержит число {numberValue}")
    @Given("property {webElementProperty} of the element {webElement} contains number {numberValue}")
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
    @Дано("свойство {webElementProperty} элемента {webElement} не содержит число {numberValue}")
    @Given("property {webElementProperty} of the element {webElement} does not contain number {numberValue}")
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
    @Дано("компонент {webElementComponent} элемента {webElement} присутствует")
    @Given("component {webElementComponent} of the element {webElement} is present")
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
    @Дано("компонент {webElementComponent} элемента {webElement} отсутствует")
    @Given("component {webElementComponent} of the element {webElement} is not present")
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
    @Дано("компонент {webElementComponent} элемента {webElement} отображается")
    @Given("component {webElementComponent} of the element {webElement} is displayed")
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
    @Дано("компонент {webElementComponent} элемента {webElement} не отображается")
    @Given("component {webElementComponent} of the element {webElement} is not displayed")
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
    @Дано("компонент {webElementComponent} элемента {webElement} имеет цвет {color} css-свойства {cssProperty}")
    @Given("component {webElementComponent} of the element {webElement} has color {color} of css-property {cssProperty}")
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
    @Дано("компонент {webElementComponent} элемента {webElement} не имеет цвет {color} css-свойства {cssProperty}")
    @Given("component {webElementComponent} of the element {webElement} does not have color {color} of css-property {cssProperty}")
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
    @Дано("компонент {webElementComponent} элемента {webElement} имеет размеры {dimensions}")
    @Given("component {webElementComponent} of the element {webElement} has dimensions {dimensions}")
    public void elementComponentHasDimensions(WebElementComponentParameter elementComponent,
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
    @Дано("компонент {webElementComponent} элемента {webElement} не имеет размеры {dimensions}")
    @Given("component {webElementComponent} of the element {webElement} does not have dimensions {dimensions}")
    public void elementComponentDoesNotHaveDimensions(WebElementComponentParameter elementComponent,
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
     * @param expectedLocation -
     */
    @Дано("компонент {webElementComponent} элемента {webElement} находится в {location}")
    @Given("component {webElementComponent} of the element {webElement} has location {location}")
    public void elementComponentHasLocation(WebElementComponentParameter elementComponent,
                                            WebElementParameter<WebChildElement> elementFinder,
                                            LocationParameter expectedLocation) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(haveLocation(elementComponent.getRaw(), expectedLocation.getLocation())));
    }


    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedLocation -
     */
    @Дано("компонент {webElementComponent} элемента {webElement} не находится в {location}")
    @Given("component {webElementComponent} of the element {webElement} does not have location {location}")
    public void elementComponentDoesNotHaveLocation(WebElementComponentParameter elementComponent,
                                                    WebElementParameter<WebChildElement> elementFinder,
                                                    LocationParameter expectedLocation) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notHaveLocation(elementComponent.getRaw(), expectedLocation.getLocation())));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedScreenshot -
     */
    @Дано("компонент {webElementComponent} элемента {webElement} выглядит как {screenshot}")
    @Given("component {webElementComponent} of the element {webElement} looks like {screenshot}")
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
    @Дано("компонент {webElementComponent} элемента {webElement} не выглядит как {screenshot}")
    @Given("component {webElementComponent} of the element {webElement} does not looks like {screenshot}")
    public void elementComponentDoesNotLooksLikeScreenshot(WebElementComponentParameter elementComponent,
                                                           WebElementParameter<WebChildElement> elementFinder,
                                                           ScreenshotParameter expectedScreenshot) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .should(notLooksLike(elementComponent.getRaw(), expectedScreenshot.getScreenshot())));
    }

}
