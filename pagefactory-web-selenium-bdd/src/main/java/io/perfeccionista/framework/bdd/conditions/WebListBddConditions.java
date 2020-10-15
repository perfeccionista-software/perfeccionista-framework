package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;

// TODO: Реализации этих методов по хорошему тоже нужно куда-то вынести.
//  Или просто для каждого модуля элементов иметь отдельную реализацию BDD
//  Или вынести в конфигурацию, где можно для конкретного метода подложить другой кондишен.
@BddFilterCondition(WebListFilterBuilder.class)
public class WebListBddConditions {

    /**
     *
     */
    @Given("without filter")
    @Given("без фильтра")
    public WebListBlockCondition withEmptyCondition() {
        return WebFilterConditions.allBlocks();
    }

    /**
     *
     * @param blockIndex -
     */
    @Given("index is {integerValue}")
    @Given("индекс {integerValue}")
    public WebListBlockCondition withIndexCondition(ValueIntegerParameter blockIndex) {
        return WebFilterConditions.blockIndex(blockIndex.getValue());
    }

    /**
     *
     * @param blockIndex -
     */
    @Given("index is not {integerValue}")
    @Given("индекс не {integerValue}")
    public WebListBlockCondition withoutIndexCondition(ValueIntegerParameter blockIndex) {
        return WebFilterConditions.blockIndexNot(blockIndex.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} exists")
    @Given("{webElement} присутствует")
    public WebListBlockCondition withElementPresentCondition(WebElementParameter<IsPresentAvailable> elementFinder) {
        return WebFilterConditions.present(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} does not exist")
    @Given("{webElement} отсутствует")
    public WebListBlockCondition withElementNotPresentCondition(WebElementParameter<IsPresentAvailable> elementFinder) {
        return WebFilterConditions.notPresent(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is displayed")
    @Given("{webElement} отображается")
    public WebListBlockCondition withElementIsDisplayedCondition(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return WebFilterConditions.displayed(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not displayed")
    @Given("{webElement} не отображается")
    public WebListBlockCondition withElementNotDisplayedCondition(WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return WebFilterConditions.notDisplayed(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is selected")
    @Given("{webElement} выделен(а|о)")
    public WebListBlockCondition withElementSelectedCondition(WebElementParameter<IsSelectedAvailable> elementFinder) {
        return WebFilterConditions.selected(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is not selected")
    @Given("{webElement} не выделен(а|о)")
    public WebListBlockCondition withElementNotSelectedCondition(WebElementParameter<IsSelectedAvailable> elementFinder) {
        return WebFilterConditions.notSelected(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is enabled")
    @Given("{webElement} доступ(ен|но|на)")
    public WebListBlockCondition withElementEnabledCondition(WebElementParameter<IsEnabledAvailable> elementFinder) {
        return WebFilterConditions.enabled(elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("{webElement} is disabled")
    @Given("{webElement} недоступ(ен|но|на)")
    public WebListBlockCondition withElementDisabledCondition(WebElementParameter<IsEnabledAvailable> elementFinder) {
        return WebFilterConditions.disabled(elementFinder.getRaw());
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
        return WebFilterConditions.containsText(elementFinder.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.notContainsText(elementFinder.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.containsText(elementFinder.getRaw(), expectedNumber.getValue());
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
        return WebFilterConditions.notContainsText(elementFinder.getRaw(), expectedNumber.getValue());
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
        return WebFilterConditions.containsLabel(elementFinder.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.notContainsLabel(elementFinder.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.containsLabel(elementFinder.getRaw(), expectedNumber.getValue());
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
        return WebFilterConditions.notContainsLabel(elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} exists")
    @Given("компонент {webElementComponent} элемента {webElement} присутствует")
    public WebListBlockCondition withElementsComponentPresentCondition(WebElementComponentParameter elementComponent,
                                                                       WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentPresent(elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} does not exist")
    @Given("компонент {webElementComponent} элемента {webElement} отсутствует")
    public WebListBlockCondition withElementsComponentNotPresentCondition(WebElementComponentParameter elementComponent,
                                                                          WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentNotPresent(elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} is displayed")
    @Given("компонент {webElementComponent} элемента {webElement} отображается")
    public WebListBlockCondition withElementsComponentIsDisplayedCondition(WebElementComponentParameter elementComponent,
                                                                           WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentDisplayed(elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("component {webElementComponent} of the {webElement} is not displayed")
    @Given("компонент {webElementComponent} элемента {webElement} не отображается")
    public WebListBlockCondition withElementsComponentNotDisplayedCondition(WebElementComponentParameter elementComponent,
                                                                            WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentNotDisplayed(elementFinder.getRaw(), elementComponent.getRaw());
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
        return WebFilterConditions.containsProperty(elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.notContainsProperty(elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
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
        return WebFilterConditions.containsProperty(elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());
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
        return WebFilterConditions.notContainsProperty(elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());
    }

}
