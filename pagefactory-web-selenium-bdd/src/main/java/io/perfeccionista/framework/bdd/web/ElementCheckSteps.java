package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.BoundsParameter;
import io.perfeccionista.framework.bdd.parameters.ColorParameter;
import io.perfeccionista.framework.bdd.parameters.ScreenshotParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;

// TODO: Написать в доке, что не все методы доступны для использования во множественном контексте
// TODO: Add to exception context limiter information
// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class ElementCheckSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} exists")
    @Given("{webElement} присутствует")
    public void elementExists(WebElementParameter<IsPresentAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsPresentAvailable::shouldBePresent);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} does not exist")
    @Given("{webElement} отсутствует")
    public void elementDoesNotExist(WebElementParameter<IsPresentAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsPresentAvailable::shouldNotBePresent);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is displayed")
    @Given("{webElement} отображается")
    public void elementIsDisplayed(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsDisplayedAvailable::shouldBeDisplayed);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not displayed")
    @Given("{webElement} не отображается")
    public void elementIsNotDisplayed(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsDisplayedAvailable::shouldNotBeDisplayed);
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveText(expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHaveText(expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveText(expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHaveText(expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveLabel(expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHaveLabel(expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveLabel(expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHaveLabel(expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHavePropertyValue(elementProperty.getRaw(), expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHavePropertyValue(elementProperty.getRaw(), expectedText.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHavePropertyValue(elementProperty.getRaw(), expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHavePropertyValue(elementProperty.getRaw(), expectedNumber.getValue()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldBePresent(elementComponent.getRaw()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldNotBePresent(elementComponent.getRaw()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldBeDisplayed(elementComponent.getRaw()));
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
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldNotBeDisplayed(elementComponent.getRaw()));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedColor -
     */
    @Given("component {webElementComponent} of the {webElement} has color {color}")
    @Given("компонент {webElementComponent} элемента {webElement} имеет цвет {color}")
    public void elementComponentHasColor(WebElementComponentParameter elementComponent,
                                         WebElementParameter<GetColorAvailable> elementFinder,
                                         ColorParameter expectedColor) {
        elementFinder.find()
                .forEachOrdered(element -> element.shouldHaveColor(elementComponent.getRaw(), expectedColor.getColor()));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedColor -
     */
    @Given("component {webElementComponent} of the {webElement} does not have color {color}")
    @Given("компонент {webElementComponent} элемента {webElement} не имеет цвет {color}")
    public void elementComponentDoesNotHaveColor(WebElementComponentParameter elementComponent,
                                                 WebElementParameter<GetColorAvailable> elementFinder,
                                                 ColorParameter expectedColor) {
        elementFinder.find()
                .forEachOrdered(element -> element.shouldNotHaveColor(elementComponent.getRaw(), expectedColor.getColor()));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedBounds -
     */
    @Given("component {webElementComponent} of the {webElement} has bounds {bounds}")
    @Given("компонент {webElementComponent} элемента {webElement} имеет размеры {bounds}")
    public void elementComponentHasBounds(WebElementComponentParameter elementComponent,
                                          WebElementParameter<GetBoundsAvailable> elementFinder,
                                          BoundsParameter expectedBounds) {
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldHaveBounds(elementComponent.getRaw(), expectedBounds.getBounds()));
    }

    /**
     *
     * @param elementComponent -
     * @param elementFinder -
     * @param expectedBounds -
     */
    @Given("component {webElementComponent} of the {webElement} does not have bounds {bounds}")
    @Given("компонент {webElementComponent} элемента {webElement} не имеет размеры {bounds}")
    public void elementComponentDoesNotHaveBounds(WebElementComponentParameter elementComponent,
                                                  WebElementParameter<GetBoundsAvailable> elementFinder,
                                                  BoundsParameter expectedBounds) {
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldNotHaveBounds(elementComponent.getRaw(), expectedBounds.getBounds()));
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
                                                    WebElementParameter<GetScreenshotAvailable> elementFinder,
                                                    ScreenshotParameter expectedScreenshot) {
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldLooksLike(elementComponent.getRaw(), expectedScreenshot.getScreenshot()));
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
                                                           WebElementParameter<GetScreenshotAvailable> elementFinder,
                                                           ScreenshotParameter expectedScreenshot) {
        elementFinder.find()
                .forEachOrdered(element -> element.componentShouldNotLooksLike(elementComponent.getRaw(), expectedScreenshot.getScreenshot()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is enabled")
    @Given("{webElement} доступ(ен|но|на)")
    public void elementIsEnabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsEnabledAvailable::shouldBeEnabled);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is disabled")
    @Given("{webElement} недоступ(ен|но|на)")
    public void elementIsDisabled(WebElementParameter<IsEnabledAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsEnabledAvailable::shouldBeDisabled);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is selected")
    @Given("{webElement} выделен(а|о)")
    public void elementIsSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsSelectedAvailable::shouldBeSelected);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not selected")
    @Given("{webElement} не выделен(а|о)")
    public void elementIsNotSelected(WebElementParameter<IsSelectedAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(IsSelectedAvailable::shouldNotBeSelected);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is in focus")
    @Given("{webElement} находится в фокусе")
    public void elementIsInFocus(WebElementParameter<WebChildElement> elementFinder) {
        elementFinder.find()
                .forEachOrdered(WebChildElement::shouldBeInFocus);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not in focus")
    @Given("{webElement} находится не в фокусе")
    public void elementIsNotInFocus(WebElementParameter<WebChildElement> elementFinder) {
        elementFinder.find()
                .forEachOrdered(WebChildElement::shouldNotBeInFocus);
    }

}
