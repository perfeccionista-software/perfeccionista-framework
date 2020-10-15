package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;

public class WebTableConditions {

    /**
     *
     */
    @Given("without filter")
    @Given("без фильтра")
    public WebTableRowCondition withEmptyCondition() {
        return WebFilterConditions.allRows();
    }

    /**
     *
     * @param rowIndex -
     */
    @Given("index is {integerValue}")
    @Given("индекс {integerValue}")
    public WebTableRowCondition withIndexCondition(ValueIntegerParameter rowIndex) {
        return WebFilterConditions.rowIndex(rowIndex.getValue());
    }

    /**
     *
     * @param rowIndex -
     */
    @Given("index is not {integerValue}")
    @Given("индекс не {integerValue}")
    public WebTableRowCondition withoutIndexCondition(ValueIntegerParameter rowIndex) {
        return WebFilterConditions.rowIndexNot(rowIndex.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} exists")
    @Given("в столбце {webTableColumn} {webElement} присутствует")
    public WebTableRowCondition withElementPresentCondition(WebTableColumnParameter tableColumn,
                                                            WebElementParameter<IsPresentAvailable> elementFinder) {
        return WebFilterConditions.present(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} does not exist")
    @Given("в столбце {webTableColumn} {webElement} отсутствует")
    public WebTableRowCondition withElementNotPresentCondition(WebTableColumnParameter tableColumn,
                                                               WebElementParameter<IsPresentAvailable> elementFinder) {
        return WebFilterConditions.notPresent(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is displayed")
    @Given("в столбце {webTableColumn} {webElement} отображается")
    public WebTableRowCondition withElementIsDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return WebFilterConditions.displayed(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is not displayed")
    @Given("в столбце {webTableColumn} {webElement} не отображается")
    public WebTableRowCondition withElementNotDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                 WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return WebFilterConditions.notDisplayed(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is selected")
    @Given("в столбце {webTableColumn} {webElement} выделен(а|о)")
    public WebTableRowCondition withElementSelectedCondition(WebTableColumnParameter tableColumn,
                                                             WebElementParameter<IsSelectedAvailable> elementFinder) {
        return WebFilterConditions.selected(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is not selected")
    @Given("в столбце {webTableColumn} {webElement} не выделен(а|о)")
    public WebTableRowCondition withElementNotSelectedCondition(WebTableColumnParameter tableColumn,
                                                                WebElementParameter<IsSelectedAvailable> elementFinder) {
        return WebFilterConditions.notSelected(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is enabled")
    @Given("в столбце {webTableColumn} {webElement} доступ(ен|но|на)")
    public WebTableRowCondition withElementEnabledCondition(WebTableColumnParameter tableColumn,
                                                            WebElementParameter<IsEnabledAvailable> elementFinder) {
        return WebFilterConditions.enabled(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is disabled")
    @Given("в столбце {webTableColumn} {webElement} недоступ(ен|но|на)")
    public WebTableRowCondition withElementDisabledCondition(WebTableColumnParameter tableColumn,
                                                             WebElementParameter<IsEnabledAvailable> elementFinder) {
        return WebFilterConditions.disabled(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} {webElement} contain {stringValue}")
    @Given("в столбце {webTableColumn} {webElement} содержит {stringValue}")
    public WebTableRowCondition withElementContainsTextCondition(WebTableColumnParameter tableColumn,
                                                                 WebElementParameter<GetTextAvailable> elementFinder,
                                                                 ValueStringParameter expectedText) {
        return WebFilterConditions.containsText(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} {webElement} не содержит {stringValue}")
    public WebTableRowCondition withElementNotContainsTextCondition(WebTableColumnParameter tableColumn,
                                                                    WebElementParameter<GetTextAvailable> elementFinder,
                                                                    ValueStringParameter expectedText) {
        return WebFilterConditions.notContainsText(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} {webElement} contain number {numberValue}")
    @Given("в столбце {webTableColumn} {webElement} содержит число {numberValue}")
    public WebTableRowCondition withElementContainsNumberCondition(WebTableColumnParameter tableColumn,
                                                                   WebElementParameter<GetTextAvailable> elementFinder,
                                                                   ValueNumberParameter expectedNumber) {
        return WebFilterConditions.containsText(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} {webElement} does not contain number {numberValue}")
    @Given("в столбце {webTableColumn} {webElement} не содержит число {numberValue}")
    public WebTableRowCondition withElementNotContainsNumberCondition(WebTableColumnParameter tableColumn,
                                                                      WebElementParameter<GetTextAvailable> elementFinder,
                                                                      ValueNumberParameter expectedNumber) {
        return WebFilterConditions.notContainsText(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} label of the {webElement} contain {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} содержит {stringValue}")
    public WebTableRowCondition withElementContainsLabelCondition(WebTableColumnParameter tableColumn,
                                                                  WebElementParameter<GetLabelAvailable> elementFinder,
                                                                  ValueStringParameter expectedText) {
        return WebFilterConditions.containsLabel(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} label of the {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} не содержит {stringValue}")
    public WebTableRowCondition withElementNotContainsLabelCondition(WebTableColumnParameter tableColumn,
                                                                     WebElementParameter<GetLabelAvailable> elementFinder,
                                                                     ValueStringParameter expectedText) {
        return WebFilterConditions.notContainsLabel(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} label of the {webElement} contain number {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} содержит число {stringValue}")
    public WebTableRowCondition withElementContainsLabelNumberCondition(WebTableColumnParameter tableColumn,
                                                                        WebElementParameter<GetLabelAvailable> elementFinder,
                                                                        ValueNumberParameter expectedNumber) {
        return WebFilterConditions.containsLabel(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} label of the {webElement} does not contain number {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} не содержит число {stringValue}")
    public WebTableRowCondition withElementNotContainsLabelNumberCondition(WebTableColumnParameter tableColumn,
                                                                           WebElementParameter<GetLabelAvailable> elementFinder,
                                                                           ValueNumberParameter expectedNumber) {
        return WebFilterConditions.notContainsLabel(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} exists")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} присутствует")
    public WebTableRowCondition withElementsComponentPresentCondition(WebTableColumnParameter tableColumn,
                                                                      WebElementComponentParameter elementComponent,
                                                                      WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentPresent(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} does not exist")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} отсутствует")
    public WebTableRowCondition withElementsComponentNotPresentCondition(WebTableColumnParameter tableColumn,
                                                                         WebElementComponentParameter elementComponent,
                                                                         WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentNotPresent(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} is displayed")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} отображается")
    public WebTableRowCondition withElementsComponentIsDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                          WebElementComponentParameter elementComponent,
                                                                          WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentDisplayed(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} is not displayed")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} не отображается")
    public WebTableRowCondition withElementsComponentNotDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                           WebElementComponentParameter elementComponent,
                                                                           WebElementParameter<WebChildElement> elementFinder) {
        return WebFilterConditions.componentNotDisplayed(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} contains {stringValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} содержит {stringValue}")
    public WebTableRowCondition withElementPropertyContainText(WebTableColumnParameter tableColumn,
                                                               WebElementPropertyParameter elementProperty,
                                                               WebElementParameter<WebChildElement> elementFinder,
                                                               ValueStringParameter expectedText) {
        return WebFilterConditions.containsProperty(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} не содержит {stringValue}")
    public WebTableRowCondition withElementPropertyDoesNotContainText(WebTableColumnParameter tableColumn,
                                                                      WebElementPropertyParameter elementProperty,
                                                                      WebElementParameter<WebChildElement> elementFinder,
                                                                      ValueStringParameter expectedText) {
        return WebFilterConditions.notContainsProperty(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} contains number {numberValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} содержит число {numberValue}")
    public WebTableRowCondition withElementPropertyContainsNumber(WebTableColumnParameter tableColumn,
                                                                  WebElementPropertyParameter elementProperty,
                                                                  WebElementParameter<WebChildElement> elementFinder,
                                                                  ValueNumberParameter expectedNumber) {
        return WebFilterConditions.containsProperty(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());

    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} does not contain number {numberValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} не содержит число {numberValue}")
    public WebTableRowCondition withElementPropertyDoesNotContainNumber(WebTableColumnParameter tableColumn,
                                                                        WebElementPropertyParameter elementProperty,
                                                                        WebElementParameter<WebChildElement> elementFinder,
                                                                        ValueNumberParameter expectedNumber) {
        return WebFilterConditions.notContainsProperty(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());
    }

}