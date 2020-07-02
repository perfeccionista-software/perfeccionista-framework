package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockTextCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableCellTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowIndexCondition;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebConditions {

    protected WebConditions() {
    }

    // WebRadioButton

    public static WebRadioButtonIndexCondition radioButtonIndex(NumberValue<Integer> integerValue) {
        return new WebRadioButtonIndexCondition(integerValue);
    }

    public static WebRadioButtonEnabledCondition enabled() {
        return new WebRadioButtonEnabledCondition();
    }

    public static WebRadioButtonEnabledCondition disabled() {
        return new WebRadioButtonEnabledCondition().inverse();
    }

    public static WebRadioButtonSelectedCondition selected() {
        return new WebRadioButtonSelectedCondition();
    }

    public static WebRadioButtonSelectedCondition notSelected() {
        return new WebRadioButtonSelectedCondition().inverse();
    }

    public static WebRadioButtonLabelCondition containsLabel(StringValue stringValue) {
        return new WebRadioButtonLabelCondition(stringValue);
    }

    public static WebRadioButtonLabelCondition notContainsLabel(StringValue stringValue) {
        return new WebRadioButtonLabelCondition(stringValue).inverse();
    }

    public static WebRadioButtonLabelCondition containsLabel(NumberValue<? extends Number> numberValue) {
        return new WebRadioButtonLabelCondition(numberValue);
    }

    public static WebRadioButtonLabelCondition notContainsLabel(NumberValue<? extends Number> numberValue) {
        return new WebRadioButtonLabelCondition(numberValue).inverse();
    }

    // WebList

    public static WebListBlockIndexCondition blockIndex(NumberValue<Integer> integerValue) {
        return new WebListBlockIndexCondition(integerValue);
    }

    public static WebListBlockElementDisplayedCondition displayed(IsDisplayedAvailable elementMock) {
        return new WebListBlockElementDisplayedCondition(elementMock);
    }

    public static WebListBlockElementDisplayedCondition notDisplayed(IsDisplayedAvailable elementMock) {
        return new WebListBlockElementDisplayedCondition(elementMock).inverse();
    }

    public static WebListBlockElementDisplayedCondition displayed(String elementName) {
        return new WebListBlockElementDisplayedCondition(elementName);
    }

    public static WebListBlockElementDisplayedCondition notDisplayed(String elementName) {
        return new WebListBlockElementDisplayedCondition(elementName).inverse();
    }

    public static WebListBlockElementPresentCondition present(IsPresentAvailable elementMock) {
        return new WebListBlockElementPresentCondition(elementMock);
    }

    public static WebListBlockElementPresentCondition notPresent(IsPresentAvailable elementMock) {
        return new WebListBlockElementPresentCondition(elementMock).inverse();
    }

    public static WebListBlockElementPresentCondition present(String elementName) {
        return new WebListBlockElementPresentCondition(elementName);
    }

    public static WebListBlockElementPresentCondition notPresent(String elementName) {
        return new WebListBlockElementPresentCondition(elementName).inverse();
    }

    public static WebListBlockElementEnabledCondition enabled(IsEnabledAvailable elementMock) {
        return new WebListBlockElementEnabledCondition(elementMock);
    }

    public static WebListBlockElementEnabledCondition disabled(IsEnabledAvailable elementMock) {
        return new WebListBlockElementEnabledCondition(elementMock).inverse();
    }

    public static WebListBlockElementEnabledCondition enabled(String elementName) {
        return new WebListBlockElementEnabledCondition(elementName);
    }

    public static WebListBlockElementEnabledCondition disabled(String elementName) {
        return new WebListBlockElementEnabledCondition(elementName).inverse();
    }

    public static WebListBlockElementSelectedCondition selected(IsSelectedAvailable elementMock) {
        return new WebListBlockElementSelectedCondition(elementMock);
    }

    public static WebListBlockElementSelectedCondition notSelected(IsSelectedAvailable elementMock) {
        return new WebListBlockElementSelectedCondition(elementMock).inverse();
    }

    public static WebListBlockElementSelectedCondition selected(String elementName) {
        return new WebListBlockElementSelectedCondition(elementName);
    }

    public static WebListBlockElementSelectedCondition notSelected(String elementName) {
        return new WebListBlockElementSelectedCondition(elementName).inverse();
    }

    public static WebListBlockElementTextCondition containsText(GetTextAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue);
    }

    public static WebListBlockElementTextCondition notContainsText(GetTextAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue).inverse();
    }

    public static WebListBlockElementTextCondition containsText(GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue);
    }

    public static WebListBlockElementTextCondition notContainsText(GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue).inverse();
    }

    public static WebListBlockElementTextCondition containsText(String elementName, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue);
    }

    public static WebListBlockElementTextCondition notContainsText(String elementName, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue).inverse();
    }

    public static WebListBlockElementTextCondition containsText(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue);
    }

    public static WebListBlockElementTextCondition notContainsText(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue).inverse();
    }

    public static WebListBlockElementLabelCondition containsLabel(GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue);
    }

    public static WebListBlockElementLabelCondition notContainsLabel(GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue).inverse();
    }

    public static WebListBlockElementLabelCondition containsLabel(GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue);
    }

    public static WebListBlockElementLabelCondition notContainsLabel(GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue).inverse();
    }

    public static WebListBlockElementLabelCondition containsLabel(String elementName, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue);
    }

    public static WebListBlockElementLabelCondition notContainsLabel(String elementName, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue).inverse();
    }

    public static WebListBlockElementLabelCondition containsLabel(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue);
    }

    public static WebListBlockElementLabelCondition notContainsLabel(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue).inverse();
    }

    public static WebListBlockElementComponentDisplayedCondition componentDisplayed(WebChildElement elementMock, String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementMock, componentName);
    }

    public static WebListBlockElementComponentDisplayedCondition componentNotDisplayed(WebChildElement elementMock, String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementMock, componentName).inverse();
    }

    public static WebListBlockElementComponentDisplayedCondition componentDisplayed(String elementName, String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementName, componentName);
    }

    public static WebListBlockElementComponentDisplayedCondition componentNotDisplayed(String elementName, String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementName, componentName).inverse();
    }

    public static WebListBlockElementComponentPresentCondition componentPresent(WebChildElement elementMock, String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementMock, componentName);
    }

    public static WebListBlockElementComponentPresentCondition componentNotPresent(WebChildElement elementMock, String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementMock, componentName).inverse();
    }

    public static WebListBlockElementComponentPresentCondition componentPresent(String elementName, String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementName, componentName);
    }

    public static WebListBlockElementComponentPresentCondition componentNotPresent(String elementName, String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementName, componentName).inverse();
    }

    public static WebListBlockElementPropertyCondition containsProperty(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition notContainsProperty(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue).inverse();
    }

    public static WebListBlockElementPropertyCondition containsProperty(WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition notContainsProperty(WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue).inverse();
    }

    public static WebListBlockElementPropertyCondition containsProperty(String elementName, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition notContainsProperty(String elementName, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue).inverse();
    }

    public static WebListBlockElementPropertyCondition containsProperty(String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition notContainsProperty(String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue).inverse();
    }

    // WebTextList

    public static WebTextListBlockIndexCondition textBlockIndex(NumberValue<Integer> integerValue) {
        return new WebTextListBlockIndexCondition(integerValue);
    }

    public static WebTextListBlockTextCondition containsTextBlock(StringValue stringValue) {
        return new WebTextListBlockTextCondition(stringValue);
    }

    public static WebTextListBlockTextCondition notContainsTextBlock(StringValue stringValue) {
        return new WebTextListBlockTextCondition(stringValue).inverse();
    }

    public static WebTextListBlockTextCondition containsTextBlock(NumberValue<? extends Number> integerValue) {
        return new WebTextListBlockTextCondition(integerValue);
    }

    public static WebTextListBlockTextCondition notContainsTextBlock(NumberValue<? extends Number> integerValue) {
        return new WebTextListBlockTextCondition(integerValue).inverse();
    }

    // WebTable

    public static WebTableRowIndexCondition rowIndex(NumberValue<Integer> integerValue) {
        return new WebTableRowIndexCondition(integerValue);
    }

    public static WebTableCellElementDisplayedCondition displayed(String columnName, IsDisplayedAvailable elementMock) {
        return new WebTableCellElementDisplayedCondition(columnName, elementMock);
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(String columnName, IsDisplayedAvailable elementMock) {
        return new WebTableCellElementDisplayedCondition(columnName, elementMock).inverse();
    }

    public static WebTableCellElementDisplayedCondition displayed(String columnName, String elementName) {
        return new WebTableCellElementDisplayedCondition(columnName, elementName);
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(String columnName, String elementName) {
        return new WebTableCellElementDisplayedCondition(columnName, elementName).inverse();
    }

    public static WebTableCellElementPresentCondition present(String columnName, IsPresentAvailable elementMock) {
        return new WebTableCellElementPresentCondition(columnName, elementMock);
    }

    public static WebTableCellElementPresentCondition notPresent(String columnName, IsPresentAvailable elementMock) {
        return new WebTableCellElementPresentCondition(columnName, elementMock).inverse();
    }

    public static WebTableCellElementPresentCondition present(String columnName, String elementName) {
        return new WebTableCellElementPresentCondition(columnName, elementName);
    }

    public static WebTableCellElementPresentCondition notPresent(String columnName, String elementName) {
        return new WebTableCellElementPresentCondition(columnName, elementName).inverse();
    }

    public static WebTableCellElementEnabledCondition enabled(String columnName, IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock);
    }

    public static WebTableCellElementEnabledCondition disabled(String columnName, IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock).inverse();
    }

    public static WebTableCellElementEnabledCondition enabled(String columnName, String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName);
    }

    public static WebTableCellElementEnabledCondition disabled(String columnName, String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName).inverse();
    }

    public static WebTableCellElementSelectedCondition selected(String columnName, IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock);
    }

    public static WebTableCellElementSelectedCondition notSelected(String columnName, IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock).inverse();
    }

    public static WebTableCellElementSelectedCondition selected(String columnName, String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName);
    }

    public static WebTableCellElementSelectedCondition notSelected(String columnName, String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName).inverse();
    }

    public static WebTableCellElementTextCondition containsText(String columnName, GetTextAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementTextCondition notContainsText(String columnName, GetTextAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue).inverse();
    }

    public static WebTableCellElementTextCondition containsText(String columnName, GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementTextCondition notContainsText(String columnName, GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue).inverse();
    }

    public static WebTableCellElementTextCondition containsText(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementTextCondition notContainsText(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue).inverse();
    }

    public static WebTableCellElementTextCondition containsText(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementTextCondition notContainsText(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue).inverse();
    }

    public static WebTableCellElementLabelCondition containsLabel(String columnName, GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementLabelCondition notContainsLabel(String columnName, GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue).inverse();
    }

    public static WebTableCellElementLabelCondition containsLabel(String columnName, GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementLabelCondition notContainsLabel(String columnName, GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue).inverse();
    }

    public static WebTableCellElementLabelCondition containsLabel(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementLabelCondition notContainsLabel(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue).inverse();
    }

    public static WebTableCellElementLabelCondition containsLabel(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementLabelCondition notContainsLabel(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue).inverse();
    }

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(String columnName, WebChildElement elementMock, String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(String columnName, WebChildElement elementMock, String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementMock, componentName).inverse();
    }

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(String columnName, String elementName, String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementName, componentName);
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(String columnName, String elementName, String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementName, componentName).inverse();
    }

    public static WebTableCellElementComponentPresentCondition componentPresent(String columnName, WebChildElement elementMock, String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(String columnName, WebChildElement elementMock, String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementMock, componentName).inverse();
    }

    public static WebTableCellElementComponentPresentCondition componentPresent(String columnName, String elementName, String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementName, componentName);
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(String columnName, String elementName, String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementName, componentName).inverse();
    }

    public static WebTableCellElementPropertyCondition containsProperty(String columnName, WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition notContainsProperty(String columnName, WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue).inverse();
    }

    public static WebTableCellElementPropertyCondition containsProperty(String columnName, WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition notContainsProperty(String columnName, WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue).inverse();
    }

    public static WebTableCellElementPropertyCondition containsProperty(String columnName, String elementName, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition notContainsProperty(String columnName, String elementName, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue).inverse();
    }

    public static WebTableCellElementPropertyCondition containsProperty(String columnName, String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition notContainsProperty(String columnName, String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue).inverse();
    }

    // WebTextTable

    public static WebTextTableRowIndexCondition textRowIndex(NumberValue<Integer> integerValue) {
        return new WebTextTableRowIndexCondition(integerValue);
    }

    public static WebTextTableCellTextCondition containsTextCell(String columnName, StringValue stringValue) {
        return new WebTextTableCellTextCondition(columnName, stringValue);
    }

    public static WebTextTableCellTextCondition notContainsTextCell(String columnName, StringValue stringValue) {
        return new WebTextTableCellTextCondition(columnName, stringValue).inverse();
    }

    public static WebTextTableCellTextCondition containsTextCell(String columnName, NumberValue<Integer> integerValue) {
        return new WebTextTableCellTextCondition(columnName, integerValue);
    }

    public static WebTextTableCellTextCondition notContainsTextCell(String columnName, NumberValue<Integer> integerValue) {
        return new WebTextTableCellTextCondition(columnName, integerValue).inverse();
    }

}
