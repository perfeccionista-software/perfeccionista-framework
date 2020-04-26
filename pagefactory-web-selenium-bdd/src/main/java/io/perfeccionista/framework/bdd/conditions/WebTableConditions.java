package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueNumberParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementComponentParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowIndexCondition;

public class WebTableConditions {

    /**
     *
     * @param blockIndex -
     */
    @Given("index {integerValue}")
    @Given("индекс {integerValue}")
    public WebTableCellCondition withIndexCondition(ValueIntegerParameter blockIndex) {
        return new WebTableRowIndexCondition(blockIndex.getValue());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} exists")
    @Given("в столбце {webTableColumn} {webElement} присутствует")
    public WebTableCellCondition withElementPresentCondition(WebTableColumnParameter tableColumn,
                                                             WebElementParameter<IsPresentAvailable> elementFinder) {
        return new WebTableCellElementPresentCondition(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} does not exist")
    @Given("в столбце {webTableColumn} {webElement} отсутствует")
    public WebTableCellCondition withElementNotPresentCondition(WebTableColumnParameter tableColumn,
                                                                WebElementParameter<IsPresentAvailable> elementFinder) {
        return new WebTableCellElementPresentCondition(tableColumn.getRaw(), elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is displayed")
    @Given("в столбце {webTableColumn} {webElement} отображается")
    public WebTableCellCondition withElementIsDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                 WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return new WebTableCellElementDisplayedCondition(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is not displayed")
    @Given("в столбце {webTableColumn} {webElement} не отображается")
    public WebTableCellCondition withElementNotDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                  WebElementParameter<IsDisplayedAvailable> elementFinder) {
        return new WebTableCellElementDisplayedCondition(tableColumn.getRaw(), elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is selected")
    @Given("в столбце {webTableColumn} {webElement} выделен(а|о)")
    public WebTableCellCondition withElementSelectedCondition(WebTableColumnParameter tableColumn,
                                                              WebElementParameter<IsSelectedAvailable> elementFinder) {
        return new WebTableCellElementSelectedCondition(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is not selected")
    @Given("в столбце {webTableColumn} {webElement} не выделен(а|о)")
    public WebTableCellCondition withElementNotSelectedCondition(WebTableColumnParameter tableColumn,
                                                                 WebElementParameter<IsSelectedAvailable> elementFinder) {
        return new WebTableCellElementSelectedCondition(tableColumn.getRaw(), elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is enabled")
    @Given("в столбце {webTableColumn} {webElement} доступ(ен|но|на)")
    public WebTableCellCondition withElementEnabledCondition(WebTableColumnParameter tableColumn,
                                                             WebElementParameter<IsEnabledAvailable> elementFinder) {
        return new WebTableCellElementEnabledCondition(tableColumn.getRaw(), elementFinder.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} {webElement} is disabled")
    @Given("в столбце {webTableColumn} {webElement} недоступ(ен|но|на)")
    public WebTableCellCondition withElementDisabledCondition(WebTableColumnParameter tableColumn,
                                                              WebElementParameter<IsEnabledAvailable> elementFinder) {
        return new WebTableCellElementEnabledCondition(tableColumn.getRaw(), elementFinder.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} {webElement} contain {stringValue}")
    @Given("в столбце {webTableColumn} {webElement} содержит {stringValue}")
    public WebTableCellCondition withElementContainsTextCondition(WebTableColumnParameter tableColumn,
                                                                  WebElementParameter<GetTextAvailable> elementFinder,
                                                                  ValueStringParameter expectedText) {
        return new WebTableCellElementTextCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} {webElement} не содержит {stringValue}")
    public WebTableCellCondition withElementNotContainsTextCondition(WebTableColumnParameter tableColumn,
                                                                     WebElementParameter<GetTextAvailable> elementFinder,
                                                                     ValueStringParameter expectedText) {
        return new WebTableCellElementTextCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} {webElement} contain number {numberValue}")
    @Given("в столбце {webTableColumn} {webElement} содержит число {numberValue}")
    public WebTableCellCondition withElementContainsNumberCondition(WebTableColumnParameter tableColumn,
                                                                    WebElementParameter<GetTextAvailable> elementFinder,
                                                                    ValueNumberParameter expectedNumber) {
        return new WebTableCellElementTextCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} {webElement} does not contain number {numberValue}")
    @Given("в столбце {webTableColumn} {webElement} не содержит число {numberValue}")
    public WebTableCellCondition withElementNotContainsNumberCondition(WebTableColumnParameter tableColumn,
                                                                       WebElementParameter<GetTextAvailable> elementFinder,
                                                                       ValueNumberParameter expectedNumber) {
        return new WebTableCellElementTextCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} label of the {webElement} contain {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} содержит {stringValue}")
    public WebTableCellCondition withElementContainsLabelCondition(WebTableColumnParameter tableColumn,
                                                                   WebElementParameter<GetLabelAvailable> elementFinder,
                                                                   ValueStringParameter expectedText) {
        return new WebTableCellElementLabelCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} label of the {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} не содержит {stringValue}")
    public WebTableCellCondition withElementNotContainsLabelCondition(WebTableColumnParameter tableColumn,
                                                                      WebElementParameter<GetLabelAvailable> elementFinder,
                                                                      ValueStringParameter expectedText) {
        return new WebTableCellElementLabelCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedText.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} label of the {webElement} contain number {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} содержит число {stringValue}")
    public WebTableCellCondition withElementContainsLabelNumberCondition(WebTableColumnParameter tableColumn,
                                                                         WebElementParameter<GetLabelAvailable> elementFinder,
                                                                         ValueNumberParameter expectedNumber) {
        return new WebTableCellElementLabelCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue());
    }

    /**
     *
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} label of the {webElement} does not contain number {stringValue}")
    @Given("в столбце {webTableColumn} лейбл {webElement} не содержит число {stringValue}")
    public WebTableCellCondition withElementNotContainsLabelNumberCondition(WebTableColumnParameter tableColumn,
                                                                            WebElementParameter<GetLabelAvailable> elementFinder,
                                                                            ValueNumberParameter expectedNumber) {
        return new WebTableCellElementLabelCondition(tableColumn.getRaw(), elementFinder.getRaw(), expectedNumber.getValue()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} exists")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} присутствует")
    public WebTableCellCondition withElementsComponentPresentCondition(WebTableColumnParameter tableColumn,
                                                                       WebElementComponentParameter elementComponent,
                                                                       WebElementParameter<WebChildElement> elementFinder) {
        return new WebTableCellElementComponentPresentCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} does not exist")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} отсутствует")
    public WebTableCellCondition withElementsComponentNotPresentCondition(WebTableColumnParameter tableColumn,
                                                                          WebElementComponentParameter elementComponent,
                                                                          WebElementParameter<WebChildElement> elementFinder) {
        return new WebTableCellElementComponentPresentCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw()).inverse();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} is displayed")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} отображается")
    public WebTableCellCondition withElementsComponentIsDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                           WebElementComponentParameter elementComponent,
                                                                           WebElementParameter<WebChildElement> elementFinder) {
        return new WebTableCellElementComponentDisplayedCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("in column {webTableColumn} component {webElementComponent} of the {webElement} is not displayed")
    @Given("в столбце {webTableColumn} компонент {webElementComponent} элемента {webElement} не отображается")
    public WebTableCellCondition withElementsComponentNotDisplayedCondition(WebTableColumnParameter tableColumn,
                                                                            WebElementComponentParameter elementComponent,
                                                                            WebElementParameter<WebChildElement> elementFinder) {
        return new WebTableCellElementComponentDisplayedCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementComponent.getRaw()).inverse();
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} contains {stringValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} содержит {stringValue}")
    public WebTableCellCondition withElementPropertyContainText(WebTableColumnParameter tableColumn,
                                                                WebElementPropertyParameter elementProperty,
                                                                WebElementParameter<WebChildElement> elementFinder,
                                                                ValueStringParameter expectedText) {
        return new WebTableCellElementPropertyCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue());
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedText -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} does not contain {stringValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} не содержит {stringValue}")
    public WebTableCellCondition withElementPropertyDoesNotContainText(WebTableColumnParameter tableColumn,
                                                                       WebElementPropertyParameter elementProperty,
                                                                       WebElementParameter<WebChildElement> elementFinder,
                                                                       ValueStringParameter expectedText) {
        return new WebTableCellElementPropertyCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedText.getValue()).inverse();

    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} contains number {numberValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} содержит число {numberValue}")
    public WebTableCellCondition withElementPropertyContainsNumber(WebTableColumnParameter tableColumn,
                                                                   WebElementPropertyParameter elementProperty,
                                                                   WebElementParameter<WebChildElement> elementFinder,
                                                                   ValueNumberParameter expectedNumber) {
        return new WebTableCellElementPropertyCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue());

    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     * @param expectedNumber -
     */
    @Given("in column {webTableColumn} property {webElementProperty} of the {webElement} does not contain number {numberValue}")
    @Given("в столбце {webTableColumn} свойство {webElementProperty} элемента {webElement} не содержит число {numberValue}")
    public WebTableCellCondition withElementPropertyDoesNotContainNumber(WebTableColumnParameter tableColumn,
                                                                         WebElementPropertyParameter elementProperty,
                                                                         WebElementParameter<WebChildElement> elementFinder,
                                                                         ValueNumberParameter expectedNumber) {
        return new WebTableCellElementPropertyCondition(tableColumn.getRaw(), elementFinder.getRaw(), elementProperty.getRaw(), expectedNumber.getValue()).inverse();

    }

}
