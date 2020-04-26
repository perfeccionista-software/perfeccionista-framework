package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockIndexCondition;

// TODO: Реализации этих методов по хорошему тоже нужно куда-то вынести.
//  Или просто для каждого модуля элементов иметь отдельную реализацию BDD
public class WebListConditions {

    /**
     *
     * @param blockIndex -
     */
    @Given("index {integerValue}")
    @Given("индекс {integerValue}")
    public WebListBlockCondition withIndexCondition(ValueIntegerParameter blockIndex) {
        return new WebListBlockIndexCondition(blockIndex.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} exists")
    @Given("{webElement} присутствует")
    public WebListBlockCondition withElementPresentCondition(WebElementParameter<IsPresentAvailable> elementFinder) {
        return new WebListBlockElementPresentCondition(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} does not exist")
    @Given("{webElement} отсутствует")
    public WebListBlockCondition withElementNotPresentCondition(WebElementParameter<IsPresentAvailable> elementFinder) {
        return new WebListBlockElementPresentCondition(elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is displayed")
    @Given("{webElement} отображается")
    public WebListBlockCondition withElementIsDisplayedCondition(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return new WebListBlockElementDisplayedCondition(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not displayed")
    @Given("{webElement} не отображается")
    public WebListBlockCondition withElementNotDisplayedCondition(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return new WebListBlockElementDisplayedCondition(elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is selected")
    @Given("{webElement} выделен(а|о)")
    public WebListBlockCondition withElementSelectedCondition(WebElementParameter<IsSelectedAvailable> elementFinder) {
        return new WebListBlockElementSelectedCondition(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not selected")
    @Given("{webElement} не выделен(а|о)")
    public WebListBlockCondition withElementNotSelectedCondition(WebElementParameter<IsSelectedAvailable> elementFinder) {
        return new WebListBlockElementSelectedCondition(elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is enabled")
    @Given("{webElement} доступ(ен|но|на)")
    public WebListBlockCondition withElementEnabledCondition(WebElementParameter<IsEnabledAvailable> elementFinder) {
        return new WebListBlockElementEnabledCondition(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is disabled")
    @Given("{webElement} недоступ(ен|но|на)")
    public WebListBlockCondition withElementDisabledCondition(WebElementParameter<IsEnabledAvailable> elementFinder) {
        return new WebListBlockElementEnabledCondition(elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("{webElement} contain {stringValue}")
    @Given("{webElement} содержит {stringValue}")
    public WebListBlockCondition withElementContainsTextCondition(WebElementParameter<GetTextAvailable> elementFinder,
                                                                  ValueStringParameter expectedText) {
        return new WebListBlockElementTextCondition(elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("{webElement} does not contain {stringValue}")
    @Given("{webElement} не содержит {stringValue}")
    public WebListBlockCondition withElementNotContainsTextCondition(WebElementParameter<GetTextAvailable> elementFinder,
                                                                     ValueStringParameter expectedText) {
        return new WebListBlockElementTextCondition(elementFinder.getRaw(), expectedText.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("{webElement} contain number {numberValue}")
    @Given("{webElement} содержит число {numberValue}")
    public WebListBlockCondition withElementContainsNumberCondition(WebElementParameter<GetTextAvailable> elementFinder,
                                                                    ValueNumberParameter expectedNumber) {
        return new WebListBlockElementTextCondition(elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("{webElement} does not contain number {numberValue}")
    @Given("{webElement} не содержит число {numberValue}")
    public WebListBlockCondition withElementNotContainsNumberCondition(WebElementParameter<GetTextAvailable> elementFinder,
                                                                       ValueNumberParameter expectedNumber) {
        return new WebListBlockElementTextCondition(elementFinder.getRaw(), expectedNumber.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("label of the {webElement} contain {stringValue}")
    @Given("лейбл {webElement} содержит {stringValue}")
    public WebListBlockCondition withElementContainsLabelCondition(WebElementParameter<GetLabelAvailable> elementFinder,
                                                                   ValueStringParameter expectedText) {
        return new WebListBlockElementLabelCondition(elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("label of the {webElement} does not contain {stringValue}")
    @Given("лейбл {webElement} не содержит {stringValue}")
    public WebListBlockCondition withElementNotContainsLabelCondition(WebElementParameter<GetLabelAvailable> elementFinder,
                                                                      ValueStringParameter expectedText) {
        return new WebListBlockElementLabelCondition(elementFinder.getRaw(), expectedText.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("label of the {webElement} contain number {stringValue}")
    @Given("лейбл {webElement} содержит число {stringValue}")
    public WebListBlockCondition withElementContainsLabelNumberCondition(WebElementParameter<GetLabelAvailable> elementFinder,
                                                                         ValueNumberParameter expectedNumber) {
        return new WebListBlockElementLabelCondition(elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("label of the {webElement} does not contain number {stringValue}")
    @Given("лейбл {webElement} не содержит число {stringValue}")
    public WebListBlockCondition withElementNotContainsLabelNumberCondition(WebElementParameter<GetLabelAvailable> elementFinder,
                                                                            ValueNumberParameter expectedNumber) {
        return new WebListBlockElementLabelCondition(elementFinder.getRaw(), expectedNumber.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} exists")
    @Given("компонент {webElementComponent} элемента {webElement} присутствует")
    public WebListBlockCondition withElementsComponentPresentCondition(WebElementComponentParameter elementComponent,
                                                                       WebElementParameter<WebChildElement> elementFinder) {
        return new WebListBlockElementComponentPresentCondition(elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} does not exist")
    @Given("компонент {webElementComponent} элемента {webElement} отсутствует")
    public WebListBlockCondition withElementsComponentNotPresentCondition(WebElementComponentParameter elementComponent,
                                                                          WebElementParameter<WebChildElement> elementFinder) {
        return new WebListBlockElementComponentPresentCondition(elementFinder.getRaw(), elementComponent.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} is displayed")
    @Given("компонент {webElementComponent} элемента {webElement} отображается")
    public WebListBlockCondition withElementsComponentIsDisplayedCondition(WebElementComponentParameter elementComponent,
                                                                           WebElementParameter<WebChildElement> elementFinder) {
        return new WebListBlockElementComponentDisplayedCondition(elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} is not displayed")
    @Given("компонент {webElementComponent} элемента {webElement} не отображается")
    public WebListBlockCondition withElementsComponentNotDisplayedCondition(WebElementComponentParameter elementComponent,
                                                                            WebElementParameter<WebChildElement> elementFinder) {
        return new WebListBlockElementComponentDisplayedCondition(elementFinder.getRaw(), elementComponent.getRaw()).inverse();
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("property {webElementProperty} of the {webElement} contains {stringValue}")
    @Given("свойство {webElementProperty} элемента {webElement} содержит {stringValue}")
    public WebListBlockCondition withElementPropertyContainText(WebElementPropertyParameter elementProperty,
                                                                WebElementParameter<WebChildElement> elementFinder,
                                                                ValueStringParameter expectedText) {
        return new WebListBlockElementPropertyCondition(elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("property {webElementProperty} of the {webElement} does not contain {stringValue}")
    @Given("свойство {webElementProperty} элемента {webElement} не содержит {stringValue}")
    public WebListBlockCondition withElementPropertyDoesNotContainText(WebElementPropertyParameter elementProperty,
                                                                       WebElementParameter<WebChildElement> elementFinder,
                                                                       ValueStringParameter expectedText) {
        return new WebListBlockElementPropertyCondition(elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue()).inverse();

    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("property {webElementProperty} of the {webElement} contains number {numberValue}")
    @Given("свойство {webElementProperty} элемента {webElement} содержит число {numberValue}")
    public WebListBlockCondition withElementPropertyContainsNumber(WebElementPropertyParameter elementProperty,
                                                                   WebElementParameter<WebChildElement> elementFinder,
                                                                   ValueNumberParameter expectedNumber) {
        return new WebListBlockElementPropertyCondition(elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());

    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("property {webElementProperty} of the {webElement} does not contain number {numberValue}")
    @Given("свойство {webElementProperty} элемента {webElement} не содержит число {numberValue}")
    public WebListBlockCondition withElementPropertyDoesNotContainNumber(WebElementPropertyParameter elementProperty,
                                                                         WebElementParameter<WebChildElement> elementFinder,
                                                                         ValueNumberParameter expectedNumber) {
        return new WebListBlockElementPropertyCondition(elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue()).inverse();

    }

}
